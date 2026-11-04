package ru.netology.domian

data class Comments (
    val count: Int = 0,                 //количество комментариев;
    val canPost: Boolean = true,        //информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = true   //информация о том, могут ли сообщества комментировать запись;
)