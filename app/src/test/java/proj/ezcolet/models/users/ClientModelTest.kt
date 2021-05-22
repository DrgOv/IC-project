package proj.ezcolet.models.users

import org.junit.Test

import org.junit.Assert.*
import proj.ezcolet.services.validation.INVALID
import proj.ezcolet.services.validation.VALID

class ClientModelTest {
    val client = ClientModel(
        "radu.mihai",
        "Radu",
        "Mihai",
        "Street 10",
        "Timis",
        "Timisoara",
        "300509",
        "0700000011",
        "radu.mihai",
        "Parola01",
        true
    )

    val client2 = ClientModel(
        "client",
        "and",
        "vju",
        "Gladiator 10",
        "Timis",
        "Timisoara",
        "300",
        "070011",
        "client",
        "xyz",
        true
    )


    @Test
    fun generateValidationMap() {
        val map1=linkedMapOf(
            "lastName" to VALID,
            "firstName" to VALID,
            "street" to VALID,
            "county" to VALID,
            "city" to VALID,
            "zipCode" to VALID,
            "phone" to VALID,
            "username" to VALID,
            "password" to VALID
        )
        val map2=linkedMapOf(
            "lastName" to INVALID,
            "firstName" to INVALID,
            "street" to VALID,
            "county" to VALID,
            "city" to VALID,
            "zipCode" to INVALID,
            "phone" to VALID,
            "username" to VALID,
            "password" to VALID
        )
        assertEquals(client.generateValidationMap(),map1)
        assertNotEquals(client.generateValidationMap(),map2)
    }

    @Test
    fun isLastNameValid() {
        assertEquals(client.isLastNameValid(), VALID)
        assertEquals(client2.isLastNameValid(), INVALID)

    }

    @Test
    fun isFirstNameValid() {
        assertEquals(client.isFirstNameValid(),VALID)
        assertEquals(client2.isFirstNameValid(), INVALID)

    }

    @Test
    fun isStreetValid() {
        assertEquals(client.isStreetValid(), VALID)
    }

    @Test
    fun isCountyValid() {
        assertEquals(client.isCountyValid(), VALID)
    }

    @Test
    fun isCityValid() {
        assertEquals(client.isCityValid(), VALID)
    }

    @Test
    fun isZipCodeValid() {
        assertEquals(client.isZipCodeValid(), VALID)
        assertEquals(client2.isZipCodeValid(), INVALID)

    }

    @Test
    fun isPhoneValid() {
        assertEquals(client.isPhoneValid(), VALID)
        assertEquals(client2.isPhoneValid(), INVALID)

    }

    @Test
    fun isUsernameValid() {
        assertEquals(client.isUsernameValid(),VALID)
        assertEquals(client2.isUsernameValid(), INVALID)

    }

    @Test
    fun isPasswordValid() {
        assertEquals(client.isPasswordValid(), VALID)
        assertEquals(client2.isPasswordValid(), INVALID)

    }
}