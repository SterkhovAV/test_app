package ru.sterkhovav.test_app.service

import org.springframework.stereotype.Service
import ru.sterkhovav.test_app.dao.models.Order
import ru.sterkhovav.test_app.dao.models.OrderRequest
import ru.sterkhovav.test_app.dao.repository.OrderRepository
import java.time.ZoneOffset

interface OrderService {
    fun getAll(): List<Order>
    fun getLastWeekOrders(): List<Order>
    fun saveRequestOrder(orderRequest: OrderRequest)
}

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val customerServiceImpl: CustomerServiceImpl,
    private val itemServiceImpl: ItemServiceImpl
) : OrderService {
    override fun getAll(): MutableList<Order> = orderRepository.findAll()

    override fun getLastWeekOrders() = orderRepository.getOrdersByPurchaseDateIsBeforeLastWeek()

    override fun saveRequestOrder(orderRequest: OrderRequest) {
        //Можно дополнительно сделать проверки корректности и на null (они в целом скорее избыточны),
        //но будем считать что валидация по xsd все это отловила
        val order = Order(
            customer = customerServiceImpl.getOrCreateCustomer(
                orderRequest.name!!,
                orderRequest.lastName!!,
                orderRequest.age!!
            ),
            purchaseItem = itemServiceImpl.getById(orderRequest.purchaseItem!!),
            count = orderRequest.count!!,
            amount = orderRequest.amount!!,
            purchaseDate = orderRequest.purchaseDate!!.toInstant().atOffset(ZoneOffset.UTC)
        )
        //Возможно сохранение дублирующихся заказов. По хорошему следует сделать аналог функции "getOrCreateCustomer для Orders
        orderRepository.save(order)
    }


}
