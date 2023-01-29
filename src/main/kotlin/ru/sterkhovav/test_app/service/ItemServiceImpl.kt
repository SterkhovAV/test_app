package ru.sterkhovav.test_app.service

import org.springframework.stereotype.Service
import ru.sterkhovav.test_app.dao.models.Item
import ru.sterkhovav.test_app.dao.repository.IItemsShortDto
import ru.sterkhovav.test_app.dao.repository.ItemRepository

interface ItemService {
    fun getLastMonthMostPopularItems(): List<IItemsShortDto>
    fun getEighteenYearChoice(): List<IItemsShortDto>
    fun getById(id: Int): Item
}

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository
) : ItemService {

    override fun getLastMonthMostPopularItems() = itemRepository.getItemsWithMaxAmountDuringLastMonth()

    override fun getEighteenYearChoice() = itemRepository.getItemsWithMaxAmountBoughtBy18YearsOld()

    override fun getById(id: Int) = itemRepository.getById(id.toLong())

}