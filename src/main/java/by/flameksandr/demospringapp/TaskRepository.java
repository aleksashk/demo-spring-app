package by.flameksandr.demospringapp;

import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    List<Task> getTasks();

    void save(Task task);

    Optional<Task> findById(UUID id);
}
