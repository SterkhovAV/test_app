package ru.sterkhovav.test_app.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.sterkhovav.test_app.controllers.endpoints.REPORTS_REQUEST_PATH
import ru.sterkhovav.test_app.service.CustomerServiceImpl
import ru.sterkhovav.test_app.service.ItemServiceImpl
import ru.sterkhovav.test_app.service.OrderServiceImpl

@Controller
@RequestMapping(path = [REPORTS_REQUEST_PATH])
class ReportsController(
    private val orderServiceImpl: OrderServiceImpl,
    private val itemServiceImpl: ItemServiceImpl,
    private val customerServiceImpl: CustomerServiceImpl
) {
    @GetMapping("/last-week-orders")
    fun lastWeekOrders(model: Model): String {
        model.addAttribute("ordersList",
            orderServiceImpl.getLastWeekOrders().map { it.toDto() }.sortedByDescending { it.purchaseDate })
        return "lastWeekOrders"
    }

    @GetMapping("/monthly-most-popular")
    fun monthlyMostPopular(model: Model): String {
        model.addAttribute("itemsList", itemServiceImpl.getLastMonthMostPopularItems())
        return "monthlyMostPopular"
    }

    @GetMapping("/half-year-customer")
    fun halfYearCustomer(model: Model): String {
        model.addAttribute("customersList", customerServiceImpl.getBestHalfYearCustomers())
        return "halfYearCustomer"
    }

    @GetMapping("/eighteen-year-choice")
    fun eighteenYearChoice(model: Model): String {
        model.addAttribute("itemsList", itemServiceImpl.getEighteenYearChoice())
        return "eighteenYearChoice"
    }
}
