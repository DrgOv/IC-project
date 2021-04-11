package proj.ezcolet.models.order

import proj.ezcolet.models.Model

data class OrderModel(
    var courierUsername: String = "",
    var clientUsername: String = "",
    var orderName: String = "",
    var orderDetails: String = "",
    var clientFirstName: String = "",
    var clientLastName: String = "",
    var clientPhone: String = "",
    var clientStreet: String = "",
    var clientCity: String = "",
    var clientCounty: String = "",
    var orderSum: String = "",
    var orderStatus: String = NORMAL
) :
    Model(orderName) {

    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderDetails='$orderDetails')"
    }
}

