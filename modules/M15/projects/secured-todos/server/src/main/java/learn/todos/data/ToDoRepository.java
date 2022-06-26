package learn.todos.data;

import learn.todos.models.ToDo;

import java.util.List;

public interface ToDoRepository {
    List<ToDo> findByAppUserId(int appUserId);

    ToDo findByIdAndAppUserId(int toDoId, int appUserId);

    ToDo create(ToDo toDo);

    boolean update(ToDo toDo);

    boolean deleteById(int toDoId);
}
