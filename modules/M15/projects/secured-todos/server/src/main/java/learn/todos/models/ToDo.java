package learn.todos.models;

import java.util.Objects;

public class ToDo {
    private int id;
    private int appUserId;
    private String description;

    public ToDo() {
    }

    public ToDo(int id, int appUserId, String description) {
        this.id = id;
        this.appUserId = appUserId;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id && appUserId == toDo.appUserId && Objects.equals(description, toDo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUserId, description);
    }
}
