import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import ru.netology.domian.Comment
import ru.netology.domian.Post
import ru.netology.domian.PostNotFoundException
import ru.netology.domian.WallService

/**
 *
 * Задача №2. JUnit — тесты на createComment
 */

class WallServiceTest {
    @Before //Нужен перед каждым тестом, чтобы каждый тест стартовал с чистого WallService
    fun clearBeforeTest() {
        WallService.clear()
    }

    // Тест на add: id после добавления не должен быть 0
    @Test
    fun addAssignsNonZeroId() {
        val post = Post(fromId = 1, text = "Тестовый пост")
        val added = WallService.add(post)

        assertNotEquals(0, added.id)
    }

    // Тест на update: пост с существующим id обновляется, возвращается true
    @Test
    fun updateExistingPostReturnsTrue() {
        // Добавляем три поста
        WallService.add(Post(fromId = 1, text = "Пост 1"))
        WallService.add(Post(fromId = 1, text = "Пост 2"))
        val toUpdate = WallService.add(Post(fromId = 1, text = "Пост 3"))

        // Готовим обновление: тот же id, другой текст
        val update = toUpdate.copy(text = "Обновлённый текст")

        val result = WallService.update(update)

        assertTrue(result)
    }

    // Тест на update: пост с несуществующим id, возвращается false
    @Test
    fun updateNonExistingPostReturnsFalse() {
        // Добавим пару постов, чтобы список был непустым
        WallService.add(Post(fromId = 1, text = "Пост 1"))
        WallService.add(Post(fromId = 1, text = "Пост 2"))

        // id = 999 гарантированно не существует
        val update = Post(id = 999, fromId = 1, text = "Призрак")

        val result = WallService.update(update)

        assertFalse(result)
    }
    // Тест 1 на createComment: добавление комментария к существующему посту
    @Test
    fun commentIsAddedToCorrectPost (){
        // Готовим два поста, чтобы было из чего выбирать
        val post1 = WallService.add(Post(fromId = 1, text = "Пост 1"))
        val post2 = WallService.add(Post(fromId = 1, text = "Пост 2"))

        //Добавим комментарий ко 2-му посту
        val createdComment = WallService.createComment(postId = post2.id, comment = Comment(fromId = 2, text = "Комментарий посту №2"))

        // Проверяем, что вернулся комментарий с выставленным id и правильным postId
        assertTrue("id комментария должен быть > 0", createdComment.id > 0)
        assertEquals("Комментарий должен быть привязан к post2", post2.id, createdComment.postId)

        // Проверяем, что комментарий действительно попал в хранилище и именно к post2
        val commentsForPost2 = WallService.getCommentsByPost(post2.id)
        assertEquals(1, commentsForPost2.size)
        assertEquals(createdComment.id, commentsForPost2.first().id)
        assertEquals("Комментарий посту №2", commentsForPost2.first().text)

        // У post1 комментариев быть не должно
        assertTrue(WallService.getCommentsByPost(post1.id).isEmpty())
    }

    // Тест 2 на createComment: добавление комментария к несуществующему посту
    @Test(expected = PostNotFoundException::class)
    fun commentIsAddedToNotExistentPost(){
        //Добавим комментарий к несуществующему посту, например Post с fromId = 12345
        //Создавать пост с таким id не будем
        WallService.createComment(postId = 12345, comment = Comment(fromId = 2, text = "Комментарий к несуществующему посту"))
    }
}