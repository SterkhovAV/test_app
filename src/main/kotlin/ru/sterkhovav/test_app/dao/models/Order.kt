package ru.sterkhovav.test_app.dao.models

import ru.sterkhovav.test_app.dto.OrderDto
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    val customer: Customer,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_item", referencedColumnName = "id")
    val purchaseItem: Item,

    @field:Column(name = "count")
    val count: Int,

    @field:Column(name = "amount")
    val amount: Double,

    @field:Column(name = "purchase_date")
    val purchaseDate: OffsetDateTime,
) {
    fun toDto() = OrderDto(
        customer = this.customer.toDto(),
        purchaseItem = this.purchaseItem.toDto(),
        count = this.count,
        amount = this.amount,
        purchaseDate = this.purchaseDate
    )
}

