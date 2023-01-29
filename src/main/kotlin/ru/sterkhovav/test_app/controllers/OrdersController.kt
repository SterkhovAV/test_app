package ru.sterkhovav.test_app.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sterkhovav.test_app.controllers.endpoints.PURCHASE_INFO_REQUEST_PATH
import ru.sterkhovav.test_app.dao.models.OrderRequest
import ru.sterkhovav.test_app.service.OrderRequestConverter
import ru.sterkhovav.test_app.service.OrderServiceImpl


@RestController
@RequestMapping(path = [PURCHASE_INFO_REQUEST_PATH])
class OrdersController(
    private val orderServiceImpl: OrderServiceImpl
) {

    @PostMapping(value = ["/new-order"], consumes = [MediaType.APPLICATION_XML_VALUE])
    fun getPerson(@RequestBody orderRequest: OrderRequest): ResponseEntity<HttpStatus> {
        orderServiceImpl.saveRequestOrder(orderRequest)
        return ResponseEntity.ok(HttpStatus.OK)
    }

}