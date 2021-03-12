package proj.ezcolet.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import proj.ezcolet.ExampleItem
import proj.ezcolet.databinding.UserHomeActivityBinding

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = UserHomeActivityBinding.inflate(layoutInflater)
        val list = generateList(500)

        binding.ordersListingRecyclerView.adapter = UserAdapter(list)
        binding.ordersListingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ordersListingRecyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    private fun generateList(size: Int): List<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {

            val item = ExampleItem("Comanda $i", "livrat la ora:")
            list += item
        }
        return list
    }
}