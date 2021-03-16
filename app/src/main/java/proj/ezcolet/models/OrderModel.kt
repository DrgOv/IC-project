package proj.ezcolet.models

data class OrderModel(
    override val id: String = "",
    val orderName: String,
    val orderDetails: String
) :
    Model(id) {
    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderDetails='$orderDetails')"
    }
}