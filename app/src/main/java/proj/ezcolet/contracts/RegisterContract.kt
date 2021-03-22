package proj.ezcolet.contracts

import proj.ezcolet.models.users.ClientModel

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
        suspend fun addClient(newClient: ClientModel): Boolean
        fun isDataValid(newClient: ClientModel): Boolean
        fun showError(field: String, error: String)
    }
}