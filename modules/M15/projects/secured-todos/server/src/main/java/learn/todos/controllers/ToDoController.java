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

    @GetMapping
    public List<ToDo> get(UsernamePasswordAuthenticationToken principal) {
        AppUser appUser = (AppUser)principal.getPrincipal();

        return toDoService.findByAppUserId(appUser.getAppUserId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id, UsernamePasswordAuthenticationToken principal) {
        AppUser appUser = (AppUser)principal.getPrincipal();

        ToDo toDo = toDoService.findByIdAndAppUserId(id, appUser.getAppUserId());

        if (toDo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(toDo);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody(required = false) ToDo toDo, UsernamePasswordAuthenticationToken principal) {
        AppUser appUser = (AppUser)principal.getPrincipal();

        // Directly set the user's ID so it's not possible to add a record for another user.
        if (toDo != null) {
            toDo.setAppUserId(appUser.getAppUserId());
        }

        Result<ToDo> result = toDoService.create(toDo);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody(required = false) ToDo toDo, UsernamePasswordAuthenticationToken principal) {
        // Immediately reject the request if the path ID doesn't match the record's ID.
        if (toDo != null && id != toDo.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        AppUser appUser = (AppUser)principal.getPrincipal();

        // Attempt to retrieve the record for the current user
        // to prevent the user from updating a record that doesn't belong to them.
        if (toDoService.findByIdAndAppUserId(id, appUser.getAppUserId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Directly set the user's ID (we already confirmed above that this record belongs to the current user).
        if (toDo != null) {
            toDo.setAppUserId(appUser.getAppUserId());
        }

        Result<ToDo> result = toDoService.update(toDo);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, UsernamePasswordAuthenticationToken principal) {
        AppUser appUser = (AppUser)principal.getPrincipal();

        // Attempt to retrieve the record for the current user
        // to prevent the user from deleting a record that doesn't belong to them.
        if (toDoService.findByIdAndAppUserId(id, appUser.getAppUserId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Result<ToDo> result = toDoService.deleteById(id);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
