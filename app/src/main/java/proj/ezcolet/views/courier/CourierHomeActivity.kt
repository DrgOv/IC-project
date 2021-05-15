package proj.ezcolet.views.courier

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.contracts.CourierHomeContract
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.presenters.courier.CourierHomePresenter
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
    private lateinit var courier_home_Presenter: CourierHomeContract.Presenter
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton
    private lateinit var infoBtn: ImageButton
    private lateinit var scanQRBtn: Button
    private var orderList: MutableList<OrderModel> = mutableListOf()
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CourierHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courier_home_Presenter = CourierHomePresenter(this)

        username = intent.getStringExtra("Username").toString()
        launch {
            courier_home_Presenter.initializeCourier(username)
            bindViews()
            if (username != null) {
                setUpRecyclerView(username)
            }
            if (username != null) {
                setListeners(username)
            }

            courier_home_Presenter.updateMonth()


            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(recyclerView)

        }
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
            finish()
            ViewService.setViewAndId(this, CourierInfoActivity(), username.toString())

        }
        scanQRBtn.setOnClickListener() {
            finish()
            ViewService.setViewAndId(this, CourierQrScanActivity(), username.toString())
        }
        exitBtn.setOnClickListener() {
            finish();
            ViewService.setView(this, LoginActivity())
        }
    }

    private fun setUpRecyclerView(username: String) {
        launch {
            val options = courier_home_Presenter.getOptions(username)
            updateOrderList(courier_home_Presenter.getDate())
            println(orderList)

            orderAdapter = CourierOrderAdapter(options, username)
            orderAdapter.startListening()

            recyclerView.adapter = orderAdapter
        }
    }

    private var simpleCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
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
                    courier_home_Presenter.updateOrders(orderList, fromPosition, toPosition)
                }


                return false

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        }


    private suspend fun updateOrderList(currentDay: String) {
        orderList = FsQueryingService.getOrdersBasedOnCourierUserNameAndDate(
            username, currentDay
        )
    }

    override fun onBackPressed() {
        //Disables back button
    }


}
