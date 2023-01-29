package ru.sterkhovav.test_app.service

import org.springframework.stereotype.Service
import ru.sterkhovav.test_app.dao.models.Order
import ru.sterkhovav.test_app.dto.OrderRequestDto
import ru.sterkhovav.test_app.dao.repository.OrderRepository
import java.time.ZoneOffset

interface OrderService {
    fun getAll(): List<Order>
    fun getLastWeekOrders(): List<Order>
    fun saveRequestOrder(orderRequestDto: OrderRequestDto)
}

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val customerServiceImpl: CustomerServiceImpl,
    private val itemServiceImpl: ItemServiceImpl
) : OrderService {
    override fun getAll(): MutableList<Order> = orderRepository.findAll()

    override fun getLastWeekOrders() = orderRepository.getOrdersByPurchaseDateIsBeforeLastWeek()

    override fun saveRequestOrder(orderRequestDto: OrderRequestDto) {
        //Можно дополнительно сделать проверки корректности и на null (они в целом скорее избыточны),
        //но будем считать что валидация по xsd все это отловила
        val order = Order(
            customer = customerServiceImpl.getOrCreateCustomer(
                orderRequestDto.name!!,
                orderRequestDto.lastName!!,
                orderRequestDto.age!!
            ),
            purchaseItem = itemServiceImpl.getById(orderRequestDto.purchaseItem!!),
            count = orderRequestDto.count!!,
            amount = orderRequestDto.amount!!,
            purchaseDate = orderRequestDto.purchaseDate!!.toInstant().atOffset(ZoneOffset.UTC)
        )
        //Возможно сохранение дублирующихся заказов. По хорошему следует сделать аналог функции "getOrCreateCustomer для Orders
        orderRepository.save(order)
    }


}
