package proj.ezcolet.views.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.ClientHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.models.users.UserModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.adapters.ClientOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity
import kotlin.coroutines.CoroutineContext

class ClientHomeActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    CoroutineScope{
    private lateinit var binding: ClientHomeActivityBinding

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton

    private lateinit var client: ClientModel
    private lateinit var order: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientHomeActivityBinding.inflate(layoutInflater)
        launch {
            client = intent.getStringExtra("id")?.let { FsDatabaseService.getClient(it) }!!
            println(client.username)
            order = FsDatabaseService.getOrdersByUsername(client.username)[0]
            println()
            val query: Query = FsDatabaseService.getOrdersCollectionReference().whereEqualTo("courierUsername", order.courierUsername)
            val options = FirestoreRecyclerOptions.Builder<OrderModel>()
                .setQuery(query, OrderModel::class.java)
                .build()
            orderAdapter = ClientOrderAdapter(options)
            orderAdapter.startListening()
            recyclerView = binding.ordersListingRecyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = orderAdapter
        }
        setContentView(binding.root)

        exitBtn = binding.exitBtn

        exitBtn.setOnClickListener() {
            goToLoginScreen()
        }
    }

    private fun goToLoginScreen() {
        finish();
        ViewService.setView(this, LoginActivity())
    }
}