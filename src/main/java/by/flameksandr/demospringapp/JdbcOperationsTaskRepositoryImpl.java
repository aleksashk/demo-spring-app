package by.flameksandr.demospringapp;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcOperationsTaskRepositoryImpl implements TaskRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcOperationsTaskRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.empty();
    }
}
