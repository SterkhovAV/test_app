package ru.sterkhovav.test_app.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import ru.sterkhovav.test_app.service.OrderServiceImpl

@Controller
class MainController(
    private val orderServiceImpl: OrderServiceImpl,
) {
    @GetMapping("/")
    fun firstPage(model: Model): String {
        model.addAttribute("ordersList", orderServiceImpl.getAll().map { it.toDto() }.sortedByDescending { it.purchaseDate })
        return "firstPage"
    }


}