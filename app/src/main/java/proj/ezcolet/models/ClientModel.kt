package proj.ezcolet.models

import com.google.firebase.firestore.Exclude

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
    val password: String
) : Model(id) {
    override fun toString(): String {
        return "ClientModel(id='$id', lastName='$lastName', firstName='$firstName', street='$street', county='$county', city='$city', zipCode='$zipCode', phone='$phone', username='$username', password='$password')"
    }

    override fun isDataValid() {
    }

    @Exclude
    fun isLastNameValid(): String {
        if (lastName.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!lastName.matches(Regex("^[A-Z][-a-zA-Z]{2,}$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isFirstNameValid(): String {
        if (firstName.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!firstName.matches(Regex("^[A-Z][-a-zA-Z]{2,}$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isStreetValid(): String {
        if (street.isEmpty()) {
            return ValidationStates.EMPTY
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isCountyValid(): String {
        if (county.isEmpty()) {
            return ValidationStates.EMPTY
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isCityValid(): String {
        if (city.isEmpty()) {
            return ValidationStates.EMPTY
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isZipCodeValid(): String {
        if (zipCode.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!zipCode.matches(Regex("^[0-9]{6}$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isPhoneValid(): String {
        if (phone.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!phone.matches(Regex("^0[0-9]{9}$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isUsernameValid(): String {
        if (username.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!username.matches(Regex("^(?=[a-zA-Z0-9._]{8,20}\$)(?!.*[_.]{2})[^_.].*[^_.]$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }

    @Exclude
    fun isPasswordValid(): String {
        if (password.isEmpty()) {
            return ValidationStates.EMPTY
        }
        if (!password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))) {
            return ValidationStates.INVALID
        }
        return ValidationStates.VALID
    }
}
