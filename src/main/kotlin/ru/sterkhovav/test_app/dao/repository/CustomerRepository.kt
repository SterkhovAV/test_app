package ru.sterkhovav.test_app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.sterkhovav.test_app.dao.models.Customer

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    @Query(
        value = """
            WITH local_t1 AS (
            SELECT customer_id , SUM("amount") as spent  FROM orders 
            JOIN customers ON (customers.id = orders.customer_id) 
            WHERE purchase_date >= (NOW() - INTERVAL '6 month')
            GROUP BY customer_id
            )
            SELECT "name" as name, last_name as lastName, local_t1.spent as spent FROM customers 
            JOIN local_t1 ON (local_t1.customer_id = customers.id) 
            WHERE local_t1.spent = (SELECT MAX(spent) FROM local_t1); 
        """,
//        countQuery = """
//            WITH local_t1 AS (
//            SELECT customer_id , SUM("amount") as spent  FROM orders
//            JOIN customers ON (customers.id = orders.customer_id)
//            WHERE purchase_date >= (NOW() - INTERVAL '6 month')
//            GROUP BY customer_id
//            )
//            SELECT COUNT(*) FROM customers
//            JOIN local_t1 ON (local_t1.customer_id = customers.id)
//            WHERE local_t1.spent = (SELECT MAX(spent) FROM local_t1);
//        """,
        nativeQuery = true
    )
    fun getCustomersWithMaxAmountDuringHalfYear(): List<ICustomerShortDto>

    fun getByNameAndLastNameAndAge(name: String, lastName: String, age: Int):Customer?
}

interface ICustomerShortDto {
    val name: String
    val lastName: String
    val spent: Double
}