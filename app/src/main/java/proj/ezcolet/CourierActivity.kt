package proj.ezcolet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_courier.*
import proj.ezcolet.databinding.ActivityCourierBinding
import proj.ezcolet.services.ViewService


class CourierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCourierBinding.inflate(layoutInflater)

        binding.infoBtn.setOnClickListener() {
            ViewService.setView(this, CourierInfoActivity())
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