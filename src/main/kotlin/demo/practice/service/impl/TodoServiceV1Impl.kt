package demo.practice.service.impl

import demo.practice.domain.TodoDto
import demo.practice.domain.Todos
import demo.practice.repository.TodoRepository
import demo.practice.service.TodoService
import org.springframework.stereotype.Service

@Service
class TodoServiceV1Impl(private val repo: TodoRepository) : TodoService {

    override fun getAll(): List<TodoDto> {
        val todoDtos = repo.findAll()
        return todoDtos
    }

    override fun create(title: String): TodoDto {
        val todoDto = repo.save(title)
        return todoDto
    }

    override fun complete(id: Int): Boolean {
        val status = repo.markDone(id)
        return status
    }
}