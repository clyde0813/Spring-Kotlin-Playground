package demo.practice.domain

import org.jetbrains.exposed.dao.id.IntIdTable

object Todos : IntIdTable("todos") {
    val title = varchar("title", 255)
    val done = bool("done").default(false)
}

data class TodoDto(
    val id: Int? = null,
    val title: String,
    val done: Boolean
)