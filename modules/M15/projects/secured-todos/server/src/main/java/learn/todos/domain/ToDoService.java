package learn.todos.domain;

import learn.todos.data.ToDoRepository;
import learn.todos.models.ToDo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public List<ToDo> findByAppUserId(int appUserId, boolean isCompleted) {
        return repository.findByAppUserId(appUserId, isCompleted);
    }

    public ToDo findByIdAndAppUserId(int toDoId, int appUserId) {
        return repository.findByIdAndAppUserId(toDoId, appUserId);
    }

    public Result<ToDo> create(ToDo toDo) {
        Result<ToDo> result = validate(toDo);

        if (toDo != null && toDo.getId() > 0) {
            result.addMessage("todo id should not be set", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            toDo = repository.create(toDo);
            result.setPayload(toDo);
        }

        return result;
    }

    public Result<ToDo> update(ToDo toDo) {
        Result<ToDo> result = validate(toDo);

        if (toDo != null && toDo.getId() <= 0) {
            result.addMessage("todo id is required", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (repository.update(toDo)) {
                result.setPayload(toDo);
            } else {
                result.addMessage("todo id %s was not found",
                        ResultType.NOT_FOUND, toDo.getId());
            }
        }

        return result;
    }

    public Result<ToDo> deleteById(int toDoId) {
        Result<ToDo> result = new Result<>();

        if (!repository.deleteById(toDoId)) {
            result.addMessage("todo id %s was not found",
                    ResultType.NOT_FOUND, toDoId);
        }

        return result;
    }

    private Result<ToDo> validate(ToDo toDo) {
        Result<ToDo> result = new Result<>();

        if (toDo == null) {
            result.addMessage("todo cannot be null", ResultType.INVALID);
            return result;
        }

        if (toDo.getDescription() == null || toDo.getDescription().isBlank()) {
            result.addMessage("todo description is required", ResultType.INVALID);
        }

        return result;
    }
}
