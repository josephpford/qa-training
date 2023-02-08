package learn.todos.controllers;

import learn.todos.domain.Result;
import learn.todos.domain.ToDoService;
import learn.todos.models.AppUser;
import learn.todos.models.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/{appUserId}/incomplete")
    public List<ToDo> getIncomplete(@PathVariable int appUserId) {
        return toDoService.findByAppUserId(appUserId, false);
    }

    @GetMapping("/{appUserId}/completed")
    public List<ToDo> getCompleted(@PathVariable int appUserId) {
        return toDoService.findByAppUserId(appUserId, true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        ToDo toDo = toDoService.findById(id);

        if (toDo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(toDo);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody(required = false) ToDo toDo) {
        Result<ToDo> result = toDoService.create(toDo);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody(required = false) ToDo toDo) {
        // Immediately reject the request if the path ID doesn't match the record's ID.
        if (toDo != null && id != toDo.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ToDo> result = toDoService.update(toDo);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Result<ToDo> result = toDoService.deleteById(id);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
