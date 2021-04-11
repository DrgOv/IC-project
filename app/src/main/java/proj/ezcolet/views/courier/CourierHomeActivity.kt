package proj.ezcolet.views.courier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.adapters.CourierOrderAdapter
import proj.ezcolet.views.entry.LoginActivity

private const val CAMERA_REQUEST_CODE = 101

class CourierHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = CourierHomeActivityBinding.inflate(layoutInflater)
        val username = intent.getStringExtra("Username")
        binding.infoBtn.setOnClickListener() {
            ViewService.setViewAndId(this, CourierInfoActivity(), username.toString())

        }
        binding.scanQRBtn.setOnClickListener() {
            ViewService.setViewAndId(this, CourierQrScanActivity(),username.toString())
        }
        binding.exitBtn.setOnClickListener() {
            finish();
            ViewService.setView(this, LoginActivity())
        }

        val list = generateList(500)
        binding.ordersListingRecyclerView.adapter = CourierOrderAdapter(list)
        binding.ordersListingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ordersListingRecyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    private fun generateList(size: Int): List<OrderModel> {
        val list = ArrayList<OrderModel>()
        for (i in 0 until size) {

            // val item = OrderModel(orderName = "Comanda $i", orderDetails =  "  livrat la ora:")
            //  list += item
        }
        return list
    }
}