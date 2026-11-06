package ru.netology

import ru.netology.domian.*

/**
 * Добавим в main различные объекты xxxAttachment.
 *
 */

fun main() {
    //Создадим пробный пост
    val firstPost = Post(
        fromId = 1,
        text = "Пустой пост",
        likes = Likes(count = 5), //Добавим 5 лайков.
        //Добавим свойство с массивом вложений. Для примера возьмем все пять типов вложений.
        attachment = listOf(
            PhotoAttachment(
                Photo(
                    id = 1,
                    ownerId = 1,
                    photo130 = "https://vk.com/photo_130",
                    photo604 = "https://vk.com/photo_604"
                )
            ),
            VideoAttachment(
                Video(
                    id = 1,
                    ownerId = 1,
                    title = "A home video",
                    duration = 3600
                )
            ),
            AudioAttachment(
                Audio(
                    id = 1,
                    ownerId = 1,
                    artist = "Artist",
                    title = "Song",
                    duration = 500
                )
            ),
            DocAttachment(
                Doc(
                    id = 1,
                    ownerId = 1,
                    title = "Title",
                    size = 1000,
                    ext = "PDF",
                    url = "https://vk.com/book_1234"
                )
            ),
            LinkAttachment(
                Link(
                    url = "https://kotlinlang.org",
                    title = "Kotlin",
                    description = "Docs"
                )
            )

        )
    )

    //Теперь используем сервис для добавления поста на стену
    val addedPost = WallService.add(firstPost)

    //Выведем результат в консоль
    println("Добавлен пост с ID: ${addedPost.id}")
    println("Текст поста: ${addedPost.text}")
    println("Количество лайков: ${addedPost.likes}")
    println("Количество вложений: ${addedPost.attachment.size}") //Выводим размер массива вложений

    //Выведем на консоль содержание массива вложений
    addedPost.attachment.forEach { attachment ->
        when (attachment) {
            is PhotoAttachment -> println("Фото: ${attachment.photo.photo604}")
            is VideoAttachment -> println("Видео: ${attachment.video.title}, Длительность: ${attachment.video.duration} сек")
            is AudioAttachment -> println("Аудио: ${attachment.audio.artist} — ${attachment.audio.title}")
            is DocAttachment -> println("Документ: ${attachment.doc.title}")
            is LinkAttachment -> println("Ссылка: ${attachment.link.url}")
        }

    }

    //Добавим комментарий к посту
    val comment = WallService.createComment(
        postId = addedPost.id,
        comment = Comment(fromId = 2, text = "Комментарий к первому посту")
    )
    println(comment)

    //отработаем исключение если такого поста нет
    try {
        WallService.createComment(
            postId = 123, comment = Comment(fromId = 3, text = "Комментарий к несуществующему посту")
        )
    } catch (exception: PostNotFoundException) {
        println("Ошибка добавления поста: ${exception.message}")
    }

    //Обновим пост: меняем текст и лайки, id оставляем тот же
    val updatePost = addedPost.copy(
        likes = Likes(count = 10)
    )
    //Запускаем обновление поста
    val updateResult = WallService.update(
        updatePost.copy(attachment = listOf(VideoAttachment(Video(duration = 1800)))))
    println("Обновленный пост: $updateResult")

    //Выведем все добавленные посты и комментарии к ним
    WallService.printPosts()
    WallService.printCommentsToPost()
}