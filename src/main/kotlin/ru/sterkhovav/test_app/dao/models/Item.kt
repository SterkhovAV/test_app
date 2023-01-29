package ru.sterkhovav.test_app.dao.models

import ru.sterkhovav.test_app.dto.ItemDto
import javax.persistence.*

@Entity
@Table(name = "items")
data class Item(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "name")
    val name: String
) {
    fun toDto() = ItemDto(this.name)
}