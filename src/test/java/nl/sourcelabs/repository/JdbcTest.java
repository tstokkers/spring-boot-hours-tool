package nl.sourcelabs.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTest {

    @Test
    public void testJdbc() throws Exception{

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        String configFile = "src/test/resources/db.properties";

        HikariConfig cfg = new HikariConfig(configFile);
        HikariDataSource ds = new HikariDataSource(cfg);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        System.out.println(jdbcTemplate.queryForList("select * from users"));
    }
}
