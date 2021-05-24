package proj.ezcolet.views.client

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.courier_info_activity.*
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
    private lateinit var courierUsernameTextView: TextView
    private lateinit var likesTextView: TextView
    private lateinit var likeImageButton: ImageButton
    private lateinit var dislikesTextView: TextView
    private lateinit var dislikeImageButton: ImageButton
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
            clientHomePresenter.setUpTexts()
            setListeners()
        }
    }

    private fun bindViews() {
        welcomeUserTextView = binding.textWelcomeUser
        courierUsernameTextView = binding.textCourierUsername
        likesTextView = binding.textLikes
        likeImageButton = binding.buttonLike
        dislikesTextView = binding.textDislikes
        dislikeImageButton = binding.buttonDislike
        exitBtn = binding.exitBtn
        recyclerView = binding.ordersListingRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun setWelcomeText(text: String) {
        welcomeUserTextView.text = text
    }

    fun setCourierUsernameText(text: String) {
        courierUsernameTextView.text = text
    }

    fun setLikes(likes: Int) {
        likesTextView.text = likes.toString()
    }

    fun setDislikes(dislikes: Int) {
        dislikesTextView.text = dislikes.toString()
    }

    fun showInvalidRatingToast() {
        Toast.makeText(this, "Ai votat deja.", Toast.LENGTH_SHORT).show()
    }

    fun showCantRateToast() {
        Toast.makeText(this, "Nu poti vota.", Toast.LENGTH_SHORT).show()
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
        likeImageButton.setOnClickListener() {
            launch { clientHomePresenter.addLike() }
        }
        dislikeImageButton.setOnClickListener() {
            launch { clientHomePresenter.addDislike() }
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