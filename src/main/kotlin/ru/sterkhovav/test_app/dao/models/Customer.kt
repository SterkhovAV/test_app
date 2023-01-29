package ru.sterkhovav.test_app.dao.models

import ru.sterkhovav.test_app.dto.CustomerDto
import javax.persistence.*

@Entity
@Table(name = "customers")
data class Customer(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "name")
    val name: String,

    @field:Column(name = "last_name")
    val lastName: String,

    @field:Column(name = "age")
    val age: Int
) {
    fun toDto() = CustomerDto(this.name, this.lastName, this.age)
}