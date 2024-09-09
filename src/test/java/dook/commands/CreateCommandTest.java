package dook.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import dook.tasks.TaskType;
import org.junit.jupiter.api.Test;

import dook.DookException;
import dook.tasks.Task;
import dook.tasks.Todo;

public class CreateCommandTest {

    private TaskListStub taskListStub = new TaskListStub(new ArrayList<Task>());
    private UiStub uiStub = new UiStub();
    private StorageStub storageStub = new StorageStub("filePath");

    @Test
    public void createTodo_execute_success() throws DookException, IOException {
        CreateCommand createTodo = new CreateCommand("Eat", TaskType.TODO);
        createTodo.execute(taskListStub, uiStub, storageStub);

        assertEquals(1, taskListStub.numOfTasks());
        assertEquals(new Todo("Eat"), taskListStub.getTask(0));
    }

    @Test
    public void createDeadline_execute_exceptionThrownInvalidDate() throws DookException, IOException {
        try {
            CreateCommand createDeadline = new CreateCommand("Eat", "12/12/2024", TaskType.DEADLINE);
            createDeadline.execute(taskListStub, uiStub, storageStub);
        } catch (Exception e) {
            assertEquals("Invalid date format. Enter your date in dd/MM/yyyy HH:mm format", e.getMessage());
        }

    }
}
