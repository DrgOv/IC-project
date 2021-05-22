package proj.ezcolet.presenters.entry

import org.junit.Test

import org.junit.Assert.*
import proj.ezcolet.models.users.ClientModel
import proj.ezcolet.views.entry.RegisterActivity

class RegisterPresenterTest {
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
        "client_user",
        "Lastname",
        "Firstname",
        "Street 10",
        "County",
        "Timisoara",
        "300509",
        "07002111",
        "client_user",
        "paro01",
        true
    )
    val result = RegisterPresenter(RegisterActivity())

    @Test
    fun isDataValid() {
        assertEquals(result.isDataValid(client), true)
        assertNotEquals(result.isDataValid(client2), true)
    }
}