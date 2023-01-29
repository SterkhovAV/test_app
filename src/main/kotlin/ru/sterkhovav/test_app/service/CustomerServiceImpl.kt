package ru.sterkhovav.test_app.service

import org.springframework.stereotype.Service
import ru.sterkhovav.test_app.dao.models.Customer
import ru.sterkhovav.test_app.dao.repository.CustomerRepository
import ru.sterkhovav.test_app.dao.repository.ICustomerShortDto


interface CustomerService {
    fun getBestHalfYearCustomers(): List<ICustomerShortDto>
    fun getOrCreateCustomer(name: String, lastName: String, age: Int): Customer
}

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {

    override fun getBestHalfYearCustomers() = customerRepository.getCustomersWithMaxAmountDuringHalfYear()
    override fun getOrCreateCustomer(name: String, lastName: String, age: Int): Customer {
        val customer = customerRepository.getByNameAndLastNameAndAge(name, lastName, age)
        return if (customer != null) customer else customerRepository.save(
            Customer(
                name = name,
                lastName = lastName,
                age = age
            )
        )
    }
}

