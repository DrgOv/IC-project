package proj.ezcolet.presenters.adapters

import proj.ezcolet.contracts.OrdersRecyclerViewContract
import proj.ezcolet.views.adapters.OrderAdapter

abstract class OrderAdapterPresenter(private val orderAdapter: OrderAdapter) : OrdersRecyclerViewContract.AdapterPresenter {
}