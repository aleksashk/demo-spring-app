package by.flameksandr.demospringapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TasksRestControllerTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    TasksRestController controller;

    @Test
    @DisplayName("GET /api/tasks/ возвращает HTTP-ответ со статусом 200 и списком задач")
    void handleGetAllTasks_ReturnsValidResponseEntity() {
        //given
        var tasks = List.of(
                new Task(UUID.randomUUID(), "First task", false),
                new Task(UUID.randomUUID(), "Second task", true)
        );

        doReturn(tasks).when(this.taskRepository).findAll();

        //when
        var responseEntity = this.controller.handleGetAllTasks();
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(tasks, responseEntity.getBody());
    }

    @Test
    void handleCreateNewTask_PayloadIsValid_ReturnsValidResponseEntity() {
        //given
        var details = "Third task";

        //when
        var responseEntity = this.controller.handleCreateNewTask(new NewTaskPayload(details),
                UriComponentsBuilder.fromUriString("http://localhost:8080"), Locale.ENGLISH);
        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        if (responseEntity.getBody() instanceof Task task) {
            assertNotNull(task.id());
            assertEquals(details, task.details());
            assertFalse(task.completed());

            assertEquals(URI.create("http://localhost:8080/api/tasks/" + task.id()),
                    responseEntity.getHeaders().getLocation());

            verify(this.taskRepository).save(task);
        }

    }

    @Test
    void handleFindTask() {
        //given

        //when

        //then
    }
}