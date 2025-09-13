package demo.practice.service

import demo.practice.config.TestDatabaseConfig
import demo.practice.domain.Todos
import demo.practice.service.impl.TodoServiceV1Impl
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
@Import(TestDatabaseConfig::class)
class TodoServiceIntegrationTest(
    @field:Autowired private val service: TodoServiceV1Impl
): StringSpec({

    beforeTest {
        transaction {
            SchemaUtils.drop(Todos)
            SchemaUtils.create(Todos)
        }
    }

    "새로운 Todo를 추가하면 목록에 나타나야 한다" {
        val todo = service.create("첫번째 todo")
        val all = service.getAll()
        all.any { it.id == todo.id } shouldBe true
    }

    "여러 Todo를 추가하면 순서대로 조회된다" {
        // given
        val todo1 = service.create("첫번째 todo")
        val todo2 = service.create("두번째 todo")
        val todo3 = service.create("세번째 todo")

        // when
        val all = service.getAll()

        // then
        all shouldHaveSize 3

        // 순서 검증
        all.map { it.title } shouldContainExactly listOf(
            "첫번째 todo",
            "두번째 todo",
            "세번째 todo"
        )

        // id 매칭 검증
        all.map { it.id } shouldContainExactly listOf(
            todo1.id,
            todo2.id,
            todo3.id
        )

        // 모든 Todo는 기본적으로 완료되지 않은 상태여야 한다
        all.forAll { it.done shouldBe false }
    }
})