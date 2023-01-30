package ru.sterkhovav.test_app.dto

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.xml.bind.annotation.XmlRootElement


@XmlRootElement
@Schema(name = "orderRequestDto")
class OrderRequestDto(
//    @field:Schema(
//        description = "A year when this car was made",
//        example = "2021",
//        type = "int",
//        minimum = "1900",
//        maximum = "2500"
//    )
    var name: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var purchaseItem: Int? = null,
    var count: Int? = null,
    var amount: Double? = null,
    var purchaseDate: Date? = null
) {
    //для отладки
    override fun toString(): String {
        return "OrderRequest(name=$name, lastName=$lastName, age=$age, purchaseItem=$purchaseItem, count=$count, amount=$amount, purchaseDate=$purchaseDate)"
    }
}

