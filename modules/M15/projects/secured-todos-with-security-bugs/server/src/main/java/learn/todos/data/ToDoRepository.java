package learn.todos.data;

import learn.todos.models.ToDo;

import java.util.List;

public interface ToDoRepository {
    List<ToDo> findByAppUserId(int appUserId, boolean isCompleted);

    ToDo findById(int toDoId);

    ToDo create(ToDo toDo);

    boolean update(ToDo toDo);

    boolean deleteById(int toDoId);
}
