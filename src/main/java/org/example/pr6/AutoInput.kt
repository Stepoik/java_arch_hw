package org.example.pr6

data class AutoInput(
    val title: String,
    val brand: String,
    val price: Double,
    val age: Int
) {
    fun toAuto(): Auto {
        return Auto(
            id = 0,
            title = title,
            brand = brand,
            price = price,
            age = age
        )
    }
}
