package proj.ezcolet.contracts

import proj.ezcolet.models.ClientModel

interface RegisterContract {
    interface View {
        fun showLastNameError(error: String)
        fun showFirstNameError(error: String)
        fun showStreetError(error: String)
        fun showCountyError(error: String)
        fun showCityError(error: String)
        fun showZipCodeError(error: String)
        fun showPhoneError(error: String)
        fun showUsernameError(error: String)
        fun showPasswordError(error: String)
        fun disableRegisterBtn()
        fun enableRegisterBtn()
        fun goToLoginScreen()
    }

    interface Presenter {
        fun addClient(newClient: ClientModel): Boolean
        fun isLastNameValid(lastName: String): Boolean
        fun isFirstNameValid(firstName: String): Boolean
        fun isStreetValid(street: String): Boolean
        fun isCountyValid(county: String): Boolean
        fun isCityValid(city: String): Boolean
        fun isZipCodeValid(zipCode: String): Boolean
        fun isPhoneValid(phone: String): Boolean
        fun isUsernameValid(username: String): Boolean
        fun isPasswordValid(password: String): Boolean
    }
}