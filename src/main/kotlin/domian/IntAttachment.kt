package ru.netology.domian

/**
 * Базовый тип вложения: type + «полезная нагрузка» в наследниках
 */
sealed interface IntAttachment {
    val type: String
}

data class PhotoAttachment(
    val photo: Photo
): IntAttachment {
    override val type: String = "Photo"
}

data class VideoAttachment(
    val video: Video
): IntAttachment {
    override val type: String = "Video"
}

data class AudioAttachment(
    val audio: Audio
): IntAttachment {
    override val type: String = "Audio"
}

data class DocAttachment(
    val doc: Doc
): IntAttachment {
    override val type: String = "Doc"
}

data class LinkAttachment(
    val link: Link
): IntAttachment {
    override val type: String = "Link"
}