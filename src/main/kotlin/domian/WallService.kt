package ru.netology.domian

/**
 * Этот объект (синглтон) будет хранить массив (список) постов.
 * Используем mutableListOf, так как он позволяет добавлять элементы (в отличие от listOf)
 *
 * Домашнее задание к занятию «3.1. Исключения»
 * Задача №1.PostNotFoundException - добавить метод createComment
 * Задача №2. JUnit — тесты на createComment
 */

object WallService {
    //Хранилище постов
    private val posts = mutableListOf<Post>()

    //создадим счетчик постов и будем присваивать каждому свой ID
    private var nextId = 1

    //хранилище комментариев
    private val comments = mutableListOf<Comment>()
    //добавим счетчик комментариев аналогично Post
    private var nextCommentId = 1

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

    // Новый метод создания комментария
    fun createComment(postId: Int, comment: Comment): Comment {
        // Проверяем, существует ли пост с таким id
        val postExists = posts.any { it.id == postId }
        if (!postExists) {
            throw PostNotFoundException("Пост с ID:$postId не найден")
        }

        // Присваиваем комментарию уникальный id и привязываем к посту
        val newComment = comment.copy(id = nextCommentId++, postId = postId)
        comments.add(newComment)
        return newComment
    }

    //Добавим Getter метод для возвращения комментария к посту по указанному идентификатору
    fun getCommentsByPost (postId: Int) : List<Comment> = comments.filter { it.postId == postId }

    //Метод для просмотра постов
    fun printPosts(){
        posts.forEach { println(it) }
    }

    //Дополнительно. Метод для просмотра комментариев к посту
    fun printCommentsToPost(){
        posts.forEach { post ->
            println("=== Пост id=${post.id}: ${post.text ?: "(без текста)"} ===")
            val postComments = comments.filter { it.postId == post.id }
            if (postComments.isEmpty()) {
                println("  (комментариев нет)")
            } else {
                postComments.forEach { println("  $it") }
            }
        }
    }

    // Сброс состояния — нужен для тестов
    fun clear() {
        posts.clear()
        nextId = 1
        //Добавим сброс комментариев
        comments.clear()
        nextCommentId = 1
    }
}