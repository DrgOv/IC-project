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

        val list = generateList(500)
        recyclerView.adapter = Adapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val binding = ActivityCourierBinding.inflate(layoutInflater)

        binding.infoImageBtn.setOnClickListener() {
            ViewService.setView(this, CourierInfoActivity())
        }

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