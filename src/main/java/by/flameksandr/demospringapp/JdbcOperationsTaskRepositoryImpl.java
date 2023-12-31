package by.flameksandr.demospringapp;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcOperationsTaskRepositoryImpl implements TaskRepository, RowMapper<Task> {

    private final JdbcOperations jdbcOperations;

    public JdbcOperationsTaskRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Task> getTasks() {
        return this.jdbcOperations.query("select * from t_task", this);
    }

    @Override
    public void save(Task task) {
        this.jdbcOperations.update("""
                insert into t_task(id, c_details, c_completes) values (?,?,?)
                """, new Object[]{task.id(), task.details(), task.completed()});
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return this.jdbcOperations.query("select * from t_task where id = ?",
                new Object[]{id},
                this).stream().findFirst();
    }

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(rs.getObject("id", UUID.class),
                rs.getString("c_details"),
                rs.getBoolean("c_completed"));
    }
}
