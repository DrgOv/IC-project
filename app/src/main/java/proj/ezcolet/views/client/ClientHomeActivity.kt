package proj.ezcolet.views.client

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.ClientHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.services.ViewService
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.services.database.FsQueryingService
import proj.ezcolet.views.adapters.ClientOrderAdapter
import proj.ezcolet.views.adapters.OrderAdapter
import proj.ezcolet.views.entry.LoginActivity
import kotlin.coroutines.CoroutineContext

class ClientHomeActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(),
    CoroutineScope {
    private lateinit var binding: ClientHomeActivityBinding

    private lateinit var welcomeUserTextView: TextView
    private lateinit var remainingOrdersNumberTextView: TextView
    private lateinit var courierUsernameTextView: TextView
    private lateinit var courierRatingTextView: TextView
    private lateinit var yourOrderNumberTextView: TextView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton

    private lateinit var client: ClientModel
    private lateinit var order: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientHomeActivityBinding.inflate(layoutInflater)
        launch {
            client = intent.getStringExtra("Username")?.let { FsClientService.getClient(it) }!!
            setContentView(binding.root)
            bindViews()
            setUpViews()
            setUpRecyclerView()
            setListeners()
        }
    }

    private fun bindViews() {
        welcomeUserTextView = binding.textWelcomeUser
        remainingOrdersNumberTextView = binding.textRemainingOrdersNumber
        courierUsernameTextView = binding.textCourierUsername
        courierRatingTextView = binding.textCourierRating
        yourOrderNumberTextView = binding.textYourOrderNumber
        exitBtn = binding.exitBtn
        recyclerView = binding.ordersListingRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpViews() {
        val welcomeUserText = "Buna ziua, ${client.firstName}!"
        welcomeUserTextView.text = welcomeUserText
    }

    private fun setUpRecyclerView() {
        launch {
            order = FsQueryingService.getOrdersByClientUsername(client.username)[0]
            val options = ViewService
                .setFsRecyclerAdapterOptions(
                    FsQueryingService.getOrdersQueryWhereEqualsTo(
                        "courierUsername", order.courierUsername
                    )
                )
            orderAdapter = ClientOrderAdapter(client, options)
            orderAdapter.startListening()

            recyclerView.adapter = orderAdapter
        }
    }

    private fun setListeners() {
        exitBtn.setOnClickListener() {
            goToLoginScreen()
        }
    }

    private fun goToLoginScreen() {
        finish();
        ViewService.setView(this, LoginActivity())
    }

    override fun onBackPressed() {
        //Disables back button
    }
}