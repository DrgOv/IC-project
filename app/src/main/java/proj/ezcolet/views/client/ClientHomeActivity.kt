package proj.ezcolet.views.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.LifecycleOwner
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
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.adapters.ClientOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity
import kotlin.coroutines.CoroutineContext

class ClientHomeActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    CoroutineScope {
    private lateinit var binding: ClientHomeActivityBinding

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton

    private lateinit var client: ClientModel
    private lateinit var order: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.ordersListingRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exitBtn = binding.exitBtn
        launch {
            client = intent.getStringExtra("Username")?.let { FsClientService.getClient(it) }!!
            order = FsQueryingService.getOrdersByClientUsername(client.username)[0]
            val options = ViewService
                .setFsRecyclerAdapterOptions(
                    FsQueryingService.getOrdersQueryWhereEqualsTo(
                        "courierUsername", order.courierUsername
                    )
                )
            orderAdapter = ClientOrderAdapter(options)
            orderAdapter.startListening()

            recyclerView.adapter = orderAdapter
        }

        exitBtn.setOnClickListener() {
            goToLoginScreen()
        }
    }

    private fun goToLoginScreen() {
        finish();
        ViewService.setView(this, LoginActivity())
    }
}