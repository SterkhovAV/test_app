package ru.sterkhovav.test_app.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.xml.bind.annotation.XmlRootElement


@XmlRootElement
@Schema(name = "orderRequestDto", description = "Добавление заказа")
class OrderRequestDto(
    @field:Schema(
        description = "Имя пользователя", example = "Валерий", type = "string", minLength = 1, maxLength = 50
    )
    var name: String? = null,
    @field:Schema(
        description = "Фамилия пользователя", example = "Петров", type = "string", minLength = 1, maxLength = 50
    )
    var lastName: String? = null,
    @field:Schema(
        description = "Возраст пользователя", example = "25", type = "int", minimum = "1", maximum = "149"
    )
    var age: Int? = null,
    @field:Schema(
        description = "id товара", example = "3", type = "int", minimum = "1", maximum = "5"
    )
    var purchaseItem: Int? = null,
    @field:Schema(
        description = "Количество товара", example = "2", type = "int", minimum = "1"
    )
    var count: Int? = null,
    @field:Schema(
        description = "Сумма заказа", example = "1425", type = "double", minimum = ">0"
    )
    var amount: Double? = null,
    @field:Schema(
        description = "Дата покупки", example = "2021-07-20T18:13:39.49", type = "date"
    )
    var purchaseDate: Date? = null
) {
    //для отладки
    override fun toString(): String {
        return "OrderRequest(name=$name, lastName=$lastName, age=$age, purchaseItem=$purchaseItem, count=$count, amount=$amount, purchaseDate=$purchaseDate)"
    }
}

