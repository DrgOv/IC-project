package proj.ezcolet.views.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import proj.ezcolet.models.OrderModel
import proj.ezcolet.databinding.ClientHomeActivityBinding
import proj.ezcolet.services.ViewService
import proj.ezcolet.views.adapters.UserAdapter
import proj.ezcolet.views.entry.LoginActivity

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ClientHomeActivityBinding.inflate(layoutInflater)
        binding.exitBtn.setOnClickListener(){
            finish();
            ViewService.setView(this, LoginActivity())
        }
        val list = generateList(500)

        binding.ordersListingRecyclerView.adapter = UserAdapter(list)
        binding.ordersListingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ordersListingRecyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    private fun generateList(size: Int): List<OrderModel> {
        val list = ArrayList<OrderModel>()
        for (i in 0 until size) {

            val item = OrderModel("Comanda $i", "livrat la ora:")
            list += item
        }
        return list
    }
}