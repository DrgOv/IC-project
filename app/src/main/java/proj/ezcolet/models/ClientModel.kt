package proj.ezcolet.models

import com.google.firebase.firestore.Exclude
import proj.ezcolet.services.validation.*

class ClientModel(
    override val id: String,
    val lastName: String,
    val firstName: String,
    val street: String,
    val county: String,
    val city: String,
    val zipCode: String,
    val phone: String,
    val username: String,
    val password: String,
    val role: String = "client"
) : Model(id) {
    override fun toString(): String {
        return "ClientModel(id='$id', lastName='$lastName', firstName='$firstName', street='$street', county='$county', city='$city', zipCode='$zipCode', phone='$phone', username='$username', password='$password', role='$role')"
    }

    @Exclude
    fun generateValidationMap(): HashMap<String, String> {
        return hashMapOf(
            "lastName" to isLastNameValid(),
            "firstName" to isFirstNameValid(),
            "street" to isStreetValid(),
            "county" to isCountyValid(),
            "city" to isCityValid(),
            "zipCode" to isZipCodeValid(),
            "phone" to isPhoneValid(),
            "username" to isUsernameValid(),
            "password" to isPasswordValid()
        )
    }

    @Exclude
    fun isLastNameValid(): String {
        return ValidationService.getStringState(lastName, PATTERN_START_UPPERCASE_MIN_3)
    }

    @Exclude
    fun isFirstNameValid(): String {
        return ValidationService.getStringState(firstName, PATTERN_START_UPPERCASE_MIN_3)
    }

    @Exclude
    fun isStreetValid(): String {
        return ValidationService.getStringState(street)
    }

    @Exclude
    fun isCountyValid(): String {
        return ValidationService.getStringState(county)
    }

    @Exclude
    fun isCityValid(): String {
        return ValidationService.getStringState(city)
    }

    @Exclude
    fun isZipCodeValid(): String {
        return ValidationService.getStringState(zipCode, PATTERN_LENGTH_6)
    }

    @Exclude
    fun isPhoneValid(): String {
        return ValidationService.getStringState(phone, PATTERN_START_0_LENGTH_10)
    }

    @Exclude
    fun isUsernameValid(): String {
        return ValidationService.getStringState(username, PATTERN_USERNAME)
    }

    @Exclude
    fun isPasswordValid(): String {
        return ValidationService.getStringState(password, PATTERN_PASSWORD)
    }
}
