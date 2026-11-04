import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import ru.netology.domian.Post
import ru.netology.domian.WallService

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
}