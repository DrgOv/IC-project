package proj.ezcolet.views.courier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.courier_info_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.R
import proj.ezcolet.contracts.CourierInfoContract
import proj.ezcolet.databinding.CourierInfoActivityBinding
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.presenters.courier.CourierInfoPresenter
import kotlin.coroutines.CoroutineContext

class CourierInfoActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(), CoroutineScope {
    private lateinit var courier_info_Presenter: CourierInfoContract.Presenter

    //private lateinit var nume: String
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_info_activity)
        courier_info_Presenter = CourierInfoPresenter(this)
        val binding = CourierInfoActivityBinding.inflate(layoutInflater)

        username = intent.getStringExtra("Username").toString()
        //courierUsernameTextView.text = username
        //println(username)
        getUser()


    }

    fun getUser() {
        launch {
            val courier = courier_info_Presenter.getUser(username)
            if (courier != null) {
                getUserData(courier)
            }
            // courierUsernameTextView.text = nume
        }
    }

    suspend fun getUserData(courier: CourierModel) {
        val firstName = courier_info_Presenter.getFirstName(courier).toString()
        val lastName = courier_info_Presenter.getLastName(courier).toString()

        courierUsernameTextView.text = (firstName + " " + lastName)

    }

}

