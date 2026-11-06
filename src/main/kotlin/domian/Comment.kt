package ru.netology.domian

/**
 * Спроектируем data class Comment со значениями по умолчанию, чтобы удобно создавать объекты.
 */

data class Comment(
    val id: Int = 0,                //Идентификатор комментария.
    val postId: Int = 0,            //К какому посту относится
    val fromId: Int = 0,            //Идентификатор автора комментария.
    val date: Long = 0L,            //Дата создания комментария в формате Unixtime.
    val text: String = "",          //Текст комментария.
    val replyToUser: Int? = null,   //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий, nullable, как в задаче про Post
    val replyToComment: Int? = null //Идентификатор комментария, в ответ на который оставлен текущий
)

    //Создадим конструктор с сообщением исключения
class PostNotFoundException (message: String) : Exception(message)