package proj.ezcolet.views.client

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.databinding.ClientHomeActivityBinding
import proj.ezcolet.models.order.OrderModel
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.presenters.client.ClientHomePresenter
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
    private lateinit var clientHomePresenter: ClientHomePresenter
    private lateinit var binding: ClientHomeActivityBinding

    private lateinit var welcomeUserTextView: TextView
    private lateinit var remainingOrdersNumberTextView: TextView
    private lateinit var courierUsernameTextView: TextView
    private lateinit var courierRatingTextView: TextView
    private lateinit var yourOrderNumberTextView: TextView
    private lateinit var orderAdapter: ClientOrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clientHomePresenter = ClientHomePresenter(this)
        launch {
            clientHomePresenter.initializeClient()
            bindViews()
            clientHomePresenter.setUpAdapter()
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

    fun setUpAdapter(client: ClientModel, options: FirestoreRecyclerOptions<OrderModel>) {
        orderAdapter = ClientOrderAdapter(client, options)
        orderAdapter.startListening()
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = orderAdapter
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