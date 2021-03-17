package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.RegisterContract
import proj.ezcolet.models.ClientModel
import proj.ezcolet.services.database.DatabaseService
import proj.ezcolet.services.database.FsDatabaseService

class RegisterPresenter(private val registerActivity: RegisterContract.View) :
    RegisterContract.Presenter {
    private val fsDatabaseService: DatabaseService = FsDatabaseService()

    override fun addClient(newClient: ClientModel): Boolean {
        if (isDataValid(newClient)) {
            println(newClient)
            fsDatabaseService.add(FsDatabaseService.CLIENTS_COLLECTION, newClient)
            return true
        }
        return false
    }

    private fun isDataValid(newClient: ClientModel): Boolean {
        val lastNameState = newClient.isLastNameValid()
        val firstNameState = newClient.isFirstNameValid()
        val streetState = newClient.isStreetValid()
        val countyState = newClient.isCountyValid()
        val cityState = newClient.isCityValid()
        val zipCodeState = newClient.isZipCodeValid()
        val phoneState = newClient.isPhoneValid()
        val usernameState = newClient.isUsernameValid()
        val passwordState = newClient.isPasswordValid()

        if (isLastNameValid(lastNameState) && isFirstNameValid(firstNameState) &&
            isStreetValid(streetState) && isCountyValid(countyState) && isCityValid(cityState) &&
            isZipCodeValid(zipCodeState) && isPhoneValid(phoneState) &&
            isUsernameValid(usernameState) && isPasswordValid(passwordState)
        ) {
            return true
        }
        return false
    }

    override fun isLastNameValid(lastName: String): Boolean {
        return when (lastName) {
            "empty" -> {
                registerActivity.showLastNameError("Introdu numele")
                false
            }
            "invalid" -> {
                registerActivity.showLastNameError("Nume invalid")
                false
            }
            else -> true
        }
    }

    override fun isFirstNameValid(firstName: String): Boolean {
        return when (firstName) {
            "empty" -> {
                registerActivity.showFirstNameError("Introdu prenumele")
                false
            }
            "invalid" -> {
                registerActivity.showFirstNameError("Prenume invalid")
                false
            }
            else -> true
        }
    }

    override fun isStreetValid(street: String): Boolean {
        return when (street) {
            "empty" -> {
                registerActivity.showStreetError("Introdu adresa")
                false
            }
            else -> true
        }
    }

    override fun isCountyValid(county: String): Boolean {
        return when (county) {
            "empty" -> {
                registerActivity.showCountyError("Introdu judetul")
                false
            }
            else -> true
        }
    }

    override fun isCityValid(city: String): Boolean {
        return when (city) {
            "empty" -> {
                registerActivity.showCityError("Introdu orasul")
                false
            }
            else -> true
        }
    }

    override fun isZipCodeValid(zipCode: String): Boolean {
        return when (zipCode) {
            "empty" -> {
                registerActivity.showZipCodeError("Introdu un cod postal")
                false
            }
            "invalid" -> {
                registerActivity.showZipCodeError("Cod postal invalid")
                false
            }
            else -> true
        }
    }

    override fun isPhoneValid(phone: String): Boolean {
        return when (phone) {
            "empty" -> {
                registerActivity.showPhoneError("Introdu un numar de telefon")
                false
            }
            "invalid" -> {
                registerActivity.showPhoneError("Numar de telefon invalid")
                false
            }
            else -> true
        }
    }

    override fun isUsernameValid(username: String): Boolean {
        return when (username) {
            "empty" -> {
                registerActivity.showUsernameError("Introdu un username")
                false
            }
            "invalid" -> {
                registerActivity.showUsernameError("Username invalid")
                false
            }
            else -> true
        }
    }

    override fun isPasswordValid(password: String): Boolean {
        return when (password) {
            "empty" -> {
                registerActivity.showPasswordError("Introdu o parola")
                false
            }
            "invalid" -> {
                registerActivity.showPasswordError("Parola invalida - minim 8 caractere, o litera mare, o litera mica, o cifra")
                false
            }
            else -> true
        }
    }
}