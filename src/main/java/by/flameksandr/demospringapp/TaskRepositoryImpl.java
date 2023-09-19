package by.flameksandr.demospringapp;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {


    private final List<Task> tasks = new LinkedList<>() {{
        this.add(new Task("First task"));
        this.add(new Task("Second task"));
    }};

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }
}
