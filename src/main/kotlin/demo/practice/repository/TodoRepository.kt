package demo.practice.repository

import demo.practice.domain.TodoDto
import demo.practice.domain.Todos
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class TodoRepository {

    fun findAll(): List<TodoDto> = transaction {
        Todos.selectAll().map { row ->
            TodoDto(row[Todos.id].value, row[Todos.title], row[Todos.done])
        }
    }

    fun save(title: String): TodoDto = transaction {
        val id = Todos.insertAndGetId {
            it[Todos.title] = title
        }
        TodoDto(id.value, title, false)
    }

    fun markDone(id: Int) = transaction {
        Todos.update(where = { Todos.id eq id }) {
            it[done] = true
        } > 0
    }
}