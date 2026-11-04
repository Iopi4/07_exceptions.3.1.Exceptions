package ru.netology.domian

/**
 * Запишем data классы для хранения данных от разного типа вложений
*/
data class Photo(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo130: String? = null,   // photo_130
    val photo604: String? = null    // photo_604
)

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val duration: Int = 0
)

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0
)

data class Doc(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Long = 0L,
    val ext: String = "",
    val url: String = ""
)

data class Link(
    val url: String = "",
    val title: String = "",
    val caption: String? = null,
    val description: String = ""
)

