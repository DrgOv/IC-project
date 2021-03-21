package proj.ezcolet.views.courier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import proj.ezcolet.models.OrderModel
import proj.ezcolet.views.adapters.CourierAdapter
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.entry.LoginActivity

private const val CAMERA_REQUEST_CODE=101
class CourierHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = CourierHomeActivityBinding.inflate(layoutInflater)

        binding.infoBtn.setOnClickListener() {
            ViewService.setView(this, CourierInfoActivity())
        }
        binding.scanQRBtn.setOnClickListener(){
            ViewService.setView(this, CourierQrScanActivity())
        }
        binding.exitBtn.setOnClickListener(){
            finish();
            //ViewService.setView(this, LoginActivity())
        }

        val list = generateList(500)
        binding.ordersListingRecyclerView.adapter = CourierAdapter(list)
        binding.ordersListingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ordersListingRecyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    private fun generateList(size: Int): List<OrderModel> {
        val list = ArrayList<OrderModel>()
        for (i in 0 until size) {

            val item = OrderModel(orderName = "Comanda $i", orderDetails =  "  livrat la ora:")
            list += item
        }
        return list
    }
}