package by.flameksandr.demospringapp;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {


    private final List<Task> tasks = new LinkedList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }
}
