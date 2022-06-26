package learn.todos.models;

import java.time.LocalDate;
import java.util.Objects;

public class ToDo {
    private int id;
    private int appUserId;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    public ToDo() {
    }

    public ToDo(int id, int appUserId, String description, LocalDate dueDate, boolean isCompleted) {
        this.id = id;
        this.appUserId = appUserId;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id && appUserId == toDo.appUserId && isCompleted == toDo.isCompleted && Objects.equals(description, toDo.description) && Objects.equals(dueDate, toDo.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUserId, description, dueDate, isCompleted);
    }
}
