package ru.sterkhovav.test_app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.sterkhovav.test_app.dao.models.Item

@Repository
interface ItemRepository : JpaRepository<Item, Long> {

    @Query(
        value = """
            WITH local_t1 AS (
            SELECT purchase_item AS item_id, SUM("count") as items_count  FROM orders WHERE purchase_date >= (NOW() - INTERVAL '1 month')
            GROUP BY purchase_item
            )
            SELECT "name" as name, items_count as itemsCount FROM items 
            JOIN local_t1 ON (local_t1.item_id = items.id) 
            WHERE local_t1.items_count = (SELECT MAX(items_count) FROM local_t1);
        """,
//        countQuery = """
//            WITH local_t1 AS (
//            SELECT purchase_item AS item_id, SUM("count") as items_count  FROM orders WHERE purchase_date >= (NOW() - INTERVAL '1 month')
//            GROUP BY purchase_item
//            )
//            SELECT COUNT(*) FROM items
//            JOIN local_t1 ON (local_t1.item_id = items.id)
//            WHERE local_t1.items_count = (SELECT MAX(items_count) FROM local_t1);
//        """,
        nativeQuery = true
    )
    fun getItemsWithMaxAmountDuringLastMonth(): List<IItemsShortDto>

    @Query(
        value = """
            WITH local_t1 AS (
            SELECT purchase_item AS item_id, SUM("count") as items_count  FROM orders 
            JOIN customers ON (customers.id = orders.customer_id) 
            WHERE customers."age" = 18
            GROUP BY purchase_item
            )
            SELECT "name" as name, items_count as itemsCount FROM items 
            JOIN local_t1 ON (local_t1.item_id = items.id) 
            WHERE local_t1.items_count = (SELECT MAX(items_count) FROM local_t1); 
        """,
//        countQuery = """
//            WITH local_t1 AS (
//            SELECT purchase_item AS item_id, SUM("count") as items_count  FROM orders
//            JOIN customers ON (customers.id = orders.customer_id)
//            WHERE customers."age" = 18
//            GROUP BY purchase_item
//            )
//            SELECT COUNT(*) FROM items
//            JOIN local_t1 ON (local_t1.item_id = items.id)
//            WHERE local_t1.items_count = (SELECT MAX(items_count) FROM local_t1);
//        """,
        nativeQuery = true
    )
    fun getItemsWithMaxAmountBoughtBy18YearsOld(): List<IItemsShortDto>

    fun getById(id: Long):Item

}

interface IItemsShortDto {
    val name: String
    val itemsCount: Int
}