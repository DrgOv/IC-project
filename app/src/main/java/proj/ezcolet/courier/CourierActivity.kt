package proj.ezcolet.courier

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import proj.ezcolet.ExampleItem
import proj.ezcolet.databinding.CourierHomeActivityBinding
import proj.ezcolet.entry.LoginActivity
import proj.ezcolet.services.ViewService

private const val CAMERA_REQUEST_CODE=101
class CourierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = CourierHomeActivityBinding.inflate(layoutInflater)

        binding.infoBtn.setOnClickListener() {
            ViewService.setView(this, CourierInfoActivity())
        }
        binding.scanQRBtn.setOnClickListener(){
            ViewService.setView(this,CourierQrScanActivity())
        }
        binding.exitBtn.setOnClickListener(){
            finish();
            ViewService.setView(this,LoginActivity())
        }

        val list = generateList(500)
        binding.ordersListingRecyclerView.adapter = Adapter(list)
        binding.ordersListingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ordersListingRecyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    private fun generateList(size: Int): List<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {

            val item = ExampleItem("Comanda $i", "  livrat la ora:")
            list += item
        }
        return list
    }
}