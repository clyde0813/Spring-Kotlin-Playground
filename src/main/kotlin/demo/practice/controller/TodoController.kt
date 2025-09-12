package demo.practice.controller

import demo.practice.domain.TodoDto
import demo.practice.service.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(private val service: TodoService) {

    @GetMapping
    fun list(): List<TodoDto> = service.getAll()

    @PostMapping
    fun add(@RequestParam title: String): TodoDto = service.create(title)

    @PostMapping("/{id}/done")
    fun complete(@PathVariable id: Int): Boolean = service.complete(id)
}