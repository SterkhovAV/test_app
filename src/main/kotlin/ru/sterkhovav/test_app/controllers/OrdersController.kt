package ru.sterkhovav.test_app.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sterkhovav.test_app.controllers.endpoints.PURCHASE_INFO_REQUEST_PATH
import ru.sterkhovav.test_app.dto.OrderRequestDto
import ru.sterkhovav.test_app.service.OrderServiceImpl

@Tag(name = "Контроллер обработки заказов")
@RestController
@RequestMapping(path = [PURCHASE_INFO_REQUEST_PATH])
class OrdersController(
    private val orderServiceImpl: OrderServiceImpl
) {
    @Operation(description = "Метод для регистрации заказа")
    @PostMapping(value = ["/new-order"], consumes = [MediaType.APPLICATION_XML_VALUE])
    fun getPerson(@RequestBody orderRequestDto: OrderRequestDto): ResponseEntity<HttpStatus> {
        orderServiceImpl.saveRequestOrder(orderRequestDto)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}