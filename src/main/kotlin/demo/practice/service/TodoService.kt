package demo.practice.service

import demo.practice.domain.TodoDto

interface TodoService {
    fun getAll(): List<TodoDto>
    fun create(title: String): TodoDto
    fun complete(id: Int): Boolean
}