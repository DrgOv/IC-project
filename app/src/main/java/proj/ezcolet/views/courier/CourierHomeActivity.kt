package proj.ezcolet.views.courier

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.adapters.ClientOrderAdapter
import proj.ezcolet.views.adapters.CourierOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity
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
            orderAdapter = CourierOrderAdapter(options)
            orderAdapter.startListening()

            recyclerView.adapter = orderAdapter
        }
    }

    override fun onBackPressed() {
        //Disables back button
    }


}
