package org.example.pr6

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Auto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    val price: Double,
    val age: Int,
    val brand: String
) {
    constructor(): this(
        id = 0,
        title = "",
        price = 0.0,
        age = 0,
        brand = ""
    )
}
