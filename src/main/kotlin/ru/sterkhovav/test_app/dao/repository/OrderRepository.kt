package ru.sterkhovav.test_app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.sterkhovav.test_app.dao.models.Order


@Repository
interface OrderRepository : JpaRepository<Order, Long> {

    @Query(
        value = """
            SELECT * FROM orders WHERE purchase_date >= (NOW() - INTERVAL '1 week')
        """,
        countQuery = """
            SELECT COUNT(*) FROM orders WHERE purchase_date >= (NOW() - INTERVAL '1 week')
        """,
        nativeQuery = true
    )
    fun getOrdersByPurchaseDateIsBeforeLastWeek(): List<Order>
}