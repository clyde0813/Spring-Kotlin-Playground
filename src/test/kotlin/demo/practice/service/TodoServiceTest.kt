package demo.practice.service

import demo.practice.config.TestDatabaseConfig
import demo.practice.service.impl.TodoServiceV1Impl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
@Import(TestDatabaseConfig::class)
class TodoServiceTest(
    @Autowired private val service: TodoServiceV1Impl
): StringSpec({

    "새로운 Todo를 추가하면 목록에 나타나야 한다" {
        val todo = service.create("첫번째 todo")
        val all = service.getAll()
        all.any { it.id == todo.id } shouldBe true
    }
})