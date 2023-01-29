package ru.sterkhovav.test_app.dto

import ru.sterkhovav.test_app.dao.models.Item

class ItemDto(
    val name: String
) {
    fun toEntity() = Item(name = this.name)
}