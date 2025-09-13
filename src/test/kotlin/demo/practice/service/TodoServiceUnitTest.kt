package demo.practice.service

import demo.practice.domain.TodoDto
import demo.practice.repository.TodoRepository
import demo.practice.service.impl.TodoServiceV1Impl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class TodoServiceUnitTest : StringSpec({

    val repo = mockk<TodoRepository>()
    val service = TodoServiceV1Impl(repo)

    "새로운 Todo 생성 시 Repo에 위임된다" {
        every { repo.save("test") } returns TodoDto(1, "test", false)

        val result = service.create("test")

        result.title shouldBe "test"
        verify { repo.save("test") }
    }
})