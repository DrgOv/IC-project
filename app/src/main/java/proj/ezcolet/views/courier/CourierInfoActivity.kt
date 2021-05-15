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
import java.util.*
import kotlin.coroutines.CoroutineContext

class CourierInfoActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(), CoroutineScope {
    private lateinit var courier_info_Presenter: CourierInfoContract.Presenter

    private lateinit var username: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var rating: String
    private lateinit var monthlyOrders: String
    private lateinit var totalOrders: String
    private lateinit var ratingMaxim: String
    private lateinit var ratingsNumber: String


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
        rating = courier_info_Presenter.getRating(courier).toString()
        monthlyOrders = courier_info_Presenter.getMonthlyOrders(courier).toString()
        totalOrders = courier_info_Presenter.getTotalOrders(courier).toString()
        ratingMaxim = courier_info_Presenter.getMaxRatings(courier).toString()
        ratingsNumber = courier_info_Presenter.getNumberRatings(courier).toString()

        courierUsernameTextView.text = (firstName + " " + lastName)
        ratingTextView.text = rating + "/10"

        val calendar: Calendar = Calendar.getInstance()
        val month: String =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ro"))

        monthlyOrdersTextView.text = "Comenzi " + month + ": " + monthlyOrders
        totalOrdersTextView.text = "Comenzi totale: " + totalOrders
        maxRatingsTextView.text = "Rating maxim: " + ratingMaxim + "/" + ratingsNumber


    }

    override fun onBackPressed() {
        finish()
        ViewService.setViewAndId(this, CourierHomeActivity(), username)
    }


}

