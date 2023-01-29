package ru.sterkhovav.test_app.dao.models

import java.util.*
import javax.xml.bind.annotation.XmlRootElement


@XmlRootElement
class OrderRequest(
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

