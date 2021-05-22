package proj.ezcolet.presenters.courier

import org.junit.Test

import org.junit.Assert.*
import proj.ezcolet.models.users.CourierModel
import proj.ezcolet.views.courier.CourierInfoActivity
import java.util.*

class CourierInfoPresenterTest {
    val result = CourierInfoPresenter(CourierInfoActivity())

    val courier = CourierModel(
        "courier_test",
        "LastName",
        "FirstName",
        "County",
        "City",
        "0712312312",
        "curier_test",
        "parola123",
        10,
        20,
        35,
        60
    )

//    @Test
//    fun getUser() {
//
//    }

    @Test
    fun getFirstName() {
        assertNotEquals(result.getFirstName(courier), "abc")
        assertNotEquals(result.getFirstName(courier), "gigi")
        assertEquals(result.getFirstName(courier), "FirstName")
    }

    @Test
    fun getLastName() {
        assertNotEquals(result.getLastName(courier), "gigi")
        assertEquals(result.getLastName(courier), "LastName")
    }

    @Test
    fun getMonthlyOrders() {
        assertNotEquals(result.getMonthlyOrders(courier), "123")
        assertEquals(result.getMonthlyOrders(courier), 35)
        assertNotEquals(result.getMonthlyOrders(courier), "35")
    }

    @Test
    fun getTotalOrders() {
        assertNotEquals(result.getTotalOrders(courier), 2000)
        assertEquals(result.getTotalOrders(courier), 60)
        assertNotEquals(result.getTotalOrders(courier), "60")
    }

    @Test
    fun getLikes() {
        assertNotEquals(result.getLikes(courier), 25)
        assertEquals(result.getLikes(courier), 10)
        assertNotEquals(result.getLikes(courier), "10")
    }

    @Test
    fun getDislikes() {
        assertNotEquals(result.getDislikes(courier), 45)
        assertEquals(result.getDislikes(courier), 20)
        assertNotEquals(result.getDislikes(courier), "20")
    }

    @Test
    fun getDate() {
        assertNotEquals(result.getDate(), "luna")
    }

    @Test
    fun calculateRatio() {
        assertEquals(result.calculateRatio(courier.likes.toDouble(), courier.dislikes.toDouble()), courier.likes.toDouble()/courier.dislikes.toDouble(), 0.0)
    }
}