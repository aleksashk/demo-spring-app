package by.flameksandr.demospringapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TasksRestControllerTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    TasksRestController controller;

    @Test
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
    void handleCreateNewTask() {
        //given

        //when

        //then
    }

    @Test
    void handleFindTask() {
        //given

        //when

        //then
    }
}