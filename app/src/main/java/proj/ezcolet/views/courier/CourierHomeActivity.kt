package proj.ezcolet.views.courier

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsOrderService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.adapters.CourierOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity
import java.util.*
import kotlin.coroutines.CoroutineContext


class CourierHomeActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    CoroutineScope {
    private lateinit var binding: CourierHomeActivityBinding

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton
    private lateinit var infoBtn: ImageButton
    private lateinit var scanQRBtn: Button
    private var orderList: MutableList<OrderModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CourierHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("Username")

        bindViews()
        if (username != null) {
            setUpRecyclerView(username)
        }
        if (username != null) {
            setListeners(username)
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun bindViews() {
        recyclerView = binding.ordersListingRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        infoBtn = binding.infoBtn
        scanQRBtn = binding.scanQRBtn
        exitBtn = binding.exitBtn
    }

    private fun setListeners(username: String) {
        infoBtn.setOnClickListener() {
            ViewService.setViewAndId(this, CourierInfoActivity(), username.toString())

        }
        scanQRBtn.setOnClickListener() {
            ViewService.setViewAndId(this, CourierQrScanActivity(), username.toString())
        }
        exitBtn.setOnClickListener() {
            finish();
            ViewService.setView(this, LoginActivity())
        }
    }

    private fun setUpRecyclerView(username: String) {
        launch {
            val options = ViewService
                .setFsRecyclerAdapterOptions(
                    FsQueryingService.getOrdersQueryWhereEqualsTo(
                        "courierUsername", username
                    )
                )
            orderList = FsQueryingService.getOrdersBasedOnCourierUserName(
                username
            )
            println(orderList)
            orderAdapter = CourierOrderAdapter(options)
            orderAdapter.startListening()

            recyclerView.adapter = orderAdapter
        }
    }

    var simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.absoluteAdapterPosition
            val toPosition = target.absoluteAdapterPosition
            Collections.swap(orderList, fromPosition, toPosition)

            recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
            println(orderList)
            println(orderList.get(fromPosition).orderNumber)
            println(orderList.get(toPosition).orderNumber)
            launch {
                updateOrders(fromPosition, toPosition)
            }


            return false

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    }

    suspend fun updateOrders(fromPosition: Int, toPosition: Int) {
        var aux = orderList.get(fromPosition).orderNumber
        orderList.get(fromPosition).orderNumber = orderList.get(toPosition).orderNumber
        orderList.get(toPosition).orderNumber = aux
        aux = orderList.get(fromPosition).id
        orderList.get(fromPosition).id = orderList.get(toPosition).id
        orderList.get(toPosition).id = aux
        println(orderList.get(fromPosition).orderNumber)
        println(orderList.get(toPosition).orderNumber)
        FsOrderService.addOrder(orderList.get(fromPosition))
        FsOrderService.addOrder(orderList.get(toPosition))

    }

    override fun onBackPressed() {
        //Disables back button
    }


}
