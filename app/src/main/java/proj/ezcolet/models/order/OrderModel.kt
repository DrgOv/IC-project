package proj.ezcolet.models.order

import proj.ezcolet.models.users.ClientModel

data class OrderModel(

    val courierUsername: String = "",
    val clientUsername: String = "",
    val orderName: String = "",
    var orderDetails: String = "-",
    val clientFirstName: String = "",
    val clientLastName: String = "",
    val clientPhone: String = "",
    val clientStreet: String = "",
    val clientCity: String = "",
    val clientCounty: String = "",
    val orderSum: String = "",
    val orderNumber: Int = 0,
    var orderStatus: String = "normal",
    val orderDate: String = ""
) :
    ModelInt(orderNumber) {

    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderNumber='$orderNumber')"
    }

    fun isClientOrder(client: ClientModel): Boolean {
        return this.clientUsername == client.username
    }
}