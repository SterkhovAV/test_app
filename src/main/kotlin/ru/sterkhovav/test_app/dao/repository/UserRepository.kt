package ru.sterkhovav.test_app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sterkhovav.test_app.dao.models.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getByUsername(username: String): User?
}