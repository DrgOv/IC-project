package proj.ezcolet.models

data class OrderModel(

    val courierUsername:String,
    val clientUsername:String,
    val orderName: String,
    val orderDetails: String,
    val clientFirstName: String,
    val clientLastName: String,
    val clientPhone: String,
    val clientStreet: String,
    val clientCity: String,
    val clientCounty: String,
    val orderSum: String
) :
    Model(orderName) {

    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderDetails='$orderDetails')"
    }
}