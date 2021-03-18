package proj.ezcolet.models

data class OrderModel(
    override val id: String = "",
    val orderName: String,
    val orderDetails: String
) :
    Model(id) {
    override fun isDataValid() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "OrderModel(id='$id', orderName='$orderName', orderDetails='$orderDetails')"
    }
}