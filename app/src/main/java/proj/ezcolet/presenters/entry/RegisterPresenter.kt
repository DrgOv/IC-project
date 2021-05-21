package proj.ezcolet.presenters.entry

import org.mindrot.jbcrypt.BCrypt
import proj.ezcolet.contracts.RegisterContract
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.services.database.FsClientService
import proj.ezcolet.services.validation.EMPTY
import proj.ezcolet.services.validation.EMPTY_ERROR_MESSAGE
import proj.ezcolet.services.validation.INVALID
import proj.ezcolet.services.validation.INVALID_ERROR_MESSAGE

class RegisterPresenter(private val registerActivity: RegisterContract.View) :
    RegisterContract.Presenter {

    override suspend fun addClient(newClient: ClientModel): Boolean {
        if (isDataValid(newClient)) {
            newClient.password = BCrypt.hashpw(newClient.password, BCrypt.gensalt())
            FsClientService.addClient(newClient)
            return true
        }
        return false
    }

    override fun isDataValid(newClient: ClientModel): Boolean {
        val validationMap = newClient.generateValidationMap()

        for ((key, value) in validationMap) {
            when (value) {
                EMPTY -> {
                    showError(key, EMPTY_ERROR_MESSAGE)
                    return false
                }
                INVALID -> {
                    var errorMessage = INVALID_ERROR_MESSAGE
                    if (key == "password") {
                        errorMessage =
                            "$INVALID_ERROR_MESSAGE - minim 8 caractere, 1 litera mare, 1 litera mica, 1 cifra"
                    }
                    showError(key, errorMessage)
                    return false
                }
            }
        }
        return true
    }

    override fun showError(field: String, error: String) {
        when (field) {
            "lastName" -> registerActivity.showLastNameError(error)
            "firstName" -> registerActivity.showFirstNameError(error)
            "street" -> registerActivity.showStreetError(error)
            "county" -> registerActivity.showCountyError(error)
            "city" -> registerActivity.showCityError(error)
            "zipCode" -> registerActivity.showZipCodeError(error)
            "phone" -> registerActivity.showPhoneError(error)
            "username" -> registerActivity.showUsernameError(error)
            "password" -> registerActivity.showPasswordError(error)
        }
    }
}