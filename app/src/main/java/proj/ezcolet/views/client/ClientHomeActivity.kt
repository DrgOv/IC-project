package proj.ezcolet.views.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import proj.ezcolet.databinding.ClientHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.adapters.ClientOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity

class ClientHomeActivity : AppCompatActivity() {
    private lateinit var binding: ClientHomeActivityBinding

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exitBtn.setOnClickListener() {
            finish();
            ViewService.setView(this, LoginActivity())
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val query: Query = FsDatabaseService.getOrdersCollectionReference()
        val options: FirestoreRecyclerOptions<OrderModel> =
            FirestoreRecyclerOptions.Builder<OrderModel>()
                .setQuery(query, OrderModel::class.java)
                .build()
        orderAdapter = ClientOrderAdapter(options)
        recyclerView = binding.ordersListingRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = orderAdapter
    }

    override fun onStart() {
        super.onStart()
        orderAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        orderAdapter.stopListening()
    }
}