package ru.sterkhovav.test_app.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
import ru.sterkhovav.test_app.utils.saveOrderRequestCorrect
import ru.sterkhovav.test_app.utils.saveOrderRequestIncorrectTag
import ru.sterkhovav.test_app.utils.saveOrderRequestIncorrectValue
import ru.sterkhovav.test_app.utils.saveOrderRequestIncorrectValueRange


@Tag(name = "Контроллер обработки заказов")
@RestController
@RequestMapping(path = [PURCHASE_INFO_REQUEST_PATH])
class OrdersController(
    private val orderServiceImpl: OrderServiceImpl
) {
    @Operation(description = "Метод для регистрации заказа")
    @PostMapping(value = ["/new-order"], consumes = [MediaType.APPLICATION_XML_VALUE])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful Operation",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, array = (
                            ArraySchema(
                                schema = Schema(implementation = OrderRequestDto::class, type = "orderRequestDto")
                            )),
                    examples = [ExampleObject(name = "Correct XML", value = saveOrderRequestCorrect)]
                )]
            ),
            ApiResponse(
                responseCode = "500", description = "Something went wrong",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, array = (
                            ArraySchema(
                                schema = Schema(implementation = OrderRequestDto::class)
                            )),
                    examples = [
                        ExampleObject(name = "Incorrect value type", value = saveOrderRequestIncorrectValue),
                        ExampleObject(name = "Incorrect tags", value = saveOrderRequestIncorrectTag),
                        ExampleObject(name = "Incorrect value range", value = saveOrderRequestIncorrectValueRange)]
                )]
            ),
        ]
    )
    fun saveOrderRequest(
        @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<HttpStatus> {
        orderServiceImpl.saveRequestOrder(orderRequestDto)
        return ResponseEntity.ok(HttpStatus.ACCEPTED)
    }
}