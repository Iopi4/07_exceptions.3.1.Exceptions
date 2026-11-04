package ru.netology.domian

/**
 * Этот объект (синглтон) будет хранить массив (список) постов.
 * Используем mutableListOf, так как он позволяет добавлять элементы (в отличие от listOf)
 */

object WallService {
    //Хранилище постов
    private val posts = mutableListOf<Post>()

    //создадим счетчик постов и будем присваивать каждому свой ID
    private var nextId = 1

    //метод для добавления поста
    fun add (post: Post): Post {
        //Присваиваем идентификатор по порядку
        val newPost = post.copy(id = nextId++)
        posts.add(newPost)
        return newPost
    }

    // Обновление записи: ищем по id, заменяем элемент, если нашли
    fun update(post: Post): Boolean {
       //Найдем индекс элемента через indexOfFirst {it.id == post.id }
        val index = posts.indexOfFirst { it.id == post.id }

        // Пост с таким id не найден
        if (index == -1) return false

        // Заменяем элемент по индексу на новый объект с теми же id, но новыми значениями полей
        posts[index] = post
        return true
    }

    //Метод для просмотра постов
    fun printPosts(){
        posts.forEach { println(it) }
    }
    // Сброс состояния — нужен для тестов
    fun clear() {
        posts.clear()
        nextId = 1
    }
}