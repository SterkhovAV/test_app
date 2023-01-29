package ru.sterkhovav.test_app.dao.models

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "username", nullable = false, unique = true)
    var username: String,

    @field:Column(name = "password", nullable = false)
    var password: String,

    @field:Column(name = "role", nullable = false)
    val role: String
)






