package learn.todos.data.mappers;

import learn.todos.models.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {
    private final List<String> roles;
    private PasswordEncoder encoder;

    public AppUserMapper(List<String> roles, PasswordEncoder encoder) {
        this.roles = roles;
        this.encoder = encoder;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        return new AppUser(
                rs.getInt("app_user_id"),
                rs.getString("username"),
                rs.getString("password"),
                encoder.encode(rs.getString("password")),
                rs.getBoolean("disabled"),
                roles);
    }
}
