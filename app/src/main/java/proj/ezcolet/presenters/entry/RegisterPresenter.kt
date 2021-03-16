package proj.ezcolet.presenters.entry

import proj.ezcolet.contracts.RegisterContract
import proj.ezcolet.models.ClientModel
import proj.ezcolet.services.database.DatabaseService
import proj.ezcolet.services.database.FsDatabaseService
import proj.ezcolet.views.entry.RegisterActivity

class RegisterPresenter(private val registerActivity: RegisterActivity) : RegisterContract.Presenter {
    private val fsDatabaseService: DatabaseService = FsDatabaseService()

    override fun addClient(newClient: ClientModel): Boolean {
        if (isLastNameValid(newClient.lastName) &&
            isFirstNameValid(newClient.firstName) &&
            isStreetValid(newClient.street) &&
            isCountyValid(newClient.county) &&
            isCityValid(newClient.city) &&
            isZipCodeValid(newClient.zipCode) &&
            isPhoneValid(newClient.phone) &&
            isUsernameValid(newClient.username) &&
            isPasswordValid(newClient.password)
        ) {
            fsDatabaseService.add(FsDatabaseService.CLIENTS_COLLECTION, newClient)
            return true
        }
        return false
    }

    override fun isLastNameValid(lastName: String): Boolean {
        if (lastName.isEmpty()) {
            registerActivity.showLastNameError("Introdu numele")
            return false
        }
        if (!lastName.matches(Regex("^[A-Z][-a-zA-Z]{2,}$"))) {
            registerActivity.showLastNameError("Nume invalid")
            return false
        }
        return true
    }

    override fun isFirstNameValid(firstName: String): Boolean {
        if (firstName.isEmpty()) {
            registerActivity.showFirstNameError("Introdu prenumele")
            return false
        }
        if (!firstName.matches(Regex("^[A-Z][-a-zA-Z]{2,}$"))) {
            registerActivity.showFirstNameError("Prenume invalid")
            return false
        }
        return true
    }

    override fun isStreetValid(street: String): Boolean {
        if (street.isEmpty()) {
            registerActivity.showStreetError("Introdu adresa")
            return false
        }
        return true
    }

    override fun isCountyValid(county: String): Boolean {
        if (county.isEmpty()) {
            registerActivity.showCountyError("Introdu judetul")
            return false
        }
        return true
    }

    override fun isCityValid(city: String): Boolean {
        if (city.isEmpty()) {
            registerActivity.showCityError("Introdu orasul")
            return false
        }
        return true
    }

    override fun isZipCodeValid(zipCode: String): Boolean {
        if (zipCode.isEmpty()) {
            registerActivity.showZipCodeError("Introdu un cod postal")
            return false
        }
        if (!zipCode.matches(Regex("^[0-9]{6}$"))) {
            registerActivity.showZipCodeError("Cod postal invalid")
            return false
        }
        return true
    }

    override fun isPhoneValid(phone: String): Boolean {
        if (phone.isEmpty()) {
            registerActivity.showPhoneError("Introdu un numar de telefon")
            return false
        }
        if (!phone.matches(Regex("^0[0-9]{9}$"))) {
            registerActivity.showPhoneError("Numar de telefon invalid")
            return false
        }
        return true
    }

    override fun isUsernameValid(username: String): Boolean {
        if (username.isEmpty()) {
            registerActivity.showUsernameError("Introdu un username")
            return false
        }
        if (!username.matches(Regex("^(?=[a-zA-Z0-9._]{8,20}\$)(?!.*[_.]{2})[^_.].*[^_.]$"))) {
            registerActivity.showUsernameError("Username invalid")
            return false
        }
        return true
    }

    override fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            registerActivity.showPasswordError("Introdu o parola")
            return false
        }
        if (!password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))) {
            registerActivity.showPasswordError("Parola invalida - minim 8 caractere, o litera mare, o litera mica, o cifra")
            return false
        }
        return true
    }
}