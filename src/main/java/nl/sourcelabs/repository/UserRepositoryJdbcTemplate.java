package nl.sourcelabs.repository;

import nl.sourcelabs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryJdbcTemplate {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("email"));

    @Autowired
    public UserRepositoryJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public User findUserByUsername(final String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "%"+ username +"%");
        return jdbcTemplate.queryForObject("select * from user where username like :username", params, USER_ROW_MAPPER);
    }
}
