package learn.todos.data;

import learn.todos.models.ToDo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ToDoJdbcTemplateRepository implements ToDoRepository {
    private final JdbcTemplate jdbcTemplate;

    public ToDoJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDo> findByAppUserId(int appUserId) {
        final String sql = "select todo_id, app_user_id, `description` "
                + "from todo "
                + "where app_user_id = ?;";

        return jdbcTemplate.query(sql, this::map, appUserId);
    }

    @Override
    public ToDo findByIdAndAppUserId(int toDoId, int appUserId) {
        final String sql = "select todo_id, app_user_id, `description` "
                + "from todo "
                + "where todo_id = ? and app_user_id = ?;";

        return jdbcTemplate.query(sql, this::map, toDoId, appUserId).stream().findFirst().orElse(null);
    }

    @Override
    public ToDo create(ToDo toDo) {
        final String sql = "insert into todo " +
                "(app_user_id, `description`) " +
                "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, toDo.getAppUserId());
            statement.setString(2, toDo.getDescription());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        toDo.setId(keyHolder.getKey().intValue());

        return toDo;
    }

    @Override
    public boolean update(ToDo toDo) {
        final String sql = "update todo set " +
                "`description` = ? " +
                "where todo_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql, toDo.getDescription(), toDo.getId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int toDoId) {
        final String sql = "delete from todo where todo_id = ?;";
        return jdbcTemplate.update(sql, toDoId) > 0;
    }

    private ToDo map(ResultSet rs, int rowId) throws SQLException {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getInt("todo_id"));
        toDo.setAppUserId(rs.getInt("app_user_id"));
        toDo.setDescription(rs.getString("description"));
        return toDo;
    }
}
