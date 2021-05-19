package proj.ezcolet.views.courier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.courier_info_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.R
import proj.ezcolet.contracts.CourierInfoContract
import proj.ezcolet.databinding.CourierInfoActivityBinding
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.presenters.courier.CourierInfoPresenter
import proj.ezcolet.services.ViewService
import kotlin.coroutines.CoroutineContext

class CourierInfoActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(), CoroutineScope {
    private lateinit var courier_info_Presenter: CourierInfoContract.Presenter

    private lateinit var username: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private var likes: Int = 0
    private lateinit var monthlyOrders: String
    private lateinit var totalOrders: String
    private var dislikes: Int = 0
    private var ratingRatio: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_info_activity)
        courier_info_Presenter = CourierInfoPresenter(this)
        CourierInfoActivityBinding.inflate(layoutInflater)

        username = intent.getStringExtra("Username").toString()

        getUser()

    }

    private fun getUser() {
        launch {
            val courier = courier_info_Presenter.getUser(username)
            if (courier != null) {
                getUserData(courier)
            }
        }
    }

    private suspend fun getUserData(courier: CourierModel) {
        firstName = courier_info_Presenter.getFirstName(courier).toString()
        lastName = courier_info_Presenter.getLastName(courier).toString()
        monthlyOrders = courier_info_Presenter.getMonthlyOrders(courier).toString()
        totalOrders = courier_info_Presenter.getTotalOrders(courier).toString()
        likes = courier_info_Presenter.getLikes(courier)!!
        dislikes = courier_info_Presenter.getDislikes(courier)!!

        courierUsernameTextView.text = (firstName + " " + lastName)
        likeTextView.text = likes.toString()
        dislikeTextView.text = dislikes.toString()

        val month = courier_info_Presenter.getDate()

        monthlyOrdersTextView.text = "Comenzi " + month + ": " + monthlyOrders
        totalOrdersTextView.text = "Total comenzi: " + totalOrders

        ratingRatio = courier_info_Presenter.calculateRatio(likes.toDouble(), dislikes.toDouble())
        ratingRatioTextView.text = "Rație rating: " + ratingRatio.toString()


    }

    override fun onBackPressed() {
        finish()
        ViewService.setViewAndId(this, CourierHomeActivity(), username)
    }


}

