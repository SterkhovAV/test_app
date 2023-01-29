package ru.sterkhovav.test_app.dto

import ru.sterkhovav.test_app.dao.models.Customer

class CustomerDto(
    val name: String,
    val lastName: String,
    val age: Int
) {
    fun toEntity() = Customer(
        name = this.name,
        lastName = this.lastName,
        age = this.age
    )
}