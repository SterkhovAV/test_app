package ru.sterkhovav.test_app.dto

import ru.sterkhovav.test_app.dao.models.Order
import java.time.OffsetDateTime

data class OrderDto(
    val customer: CustomerDto,
    val purchaseItem: ItemDto,
    val count: Int,
    val amount: Double,
    val purchaseDate: OffsetDateTime,
) {
    fun toEntity() = Order(
        customer = this.customer.toEntity(),
        purchaseItem = this.purchaseItem.toEntity(),
        count = this.count,
        amount = this.amount,
        purchaseDate = this.purchaseDate
    )
}
