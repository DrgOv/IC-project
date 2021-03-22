package proj.ezcolet.models

data class OrderModel(
    override val id: String = "",
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
    Model(id) {

    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderDetails='$orderDetails')"
    }
}