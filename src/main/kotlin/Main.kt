package ru.netology
import ru.netology.domian.Likes
import ru.netology.domian.Post
import ru.netology.domian.WallService


fun main() {
    //Создадим пробный пост
    val firstPost = Post(
        fromId = 1,
        text = "Пустой пост",
        likes = Likes(count = 5), //добавим 5 лайков.
    )

    //Теперь используем сервис для добавления поста на стену
    val addedPost = WallService.add(firstPost)

    //Выведем результат в консоль
    println("Добавлен пост с ID: ${addedPost.id}")
    println("Текст поста: ${addedPost.text}")
    println("Количество лайков: ${addedPost.likes}")

    //Обновим пост: меняем текст и лайки, id оставляем тот же
    val updatePost = addedPost.copy(likes = Likes(count = 10)
    )
    //Запускаем обновление поста
    val updateResult = WallService.update(updatePost)
    println("Обновленный пост: $updateResult")

    //Выведем все добавленные посты
    WallService.printPosts()
}