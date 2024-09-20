package dook.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dook.DookException;
import dook.tasks.Task;
import dook.tasks.TaskType;
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
    public void testExecute_addDeadline_success() throws DookException, IOException {
        String by = "25/09/2024 23:59";
        CreateCommand command = new CreateCommand("Submit report", by, TaskType.DEADLINE);
        String result = command.execute(taskListStub, uiStub, storageStub);

        assertEquals(1, taskListStub.numOfTasks());
        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("Submit report"));
    }

    @Test
    public void testExecute_addEvent_success() throws DookException, IOException {
        String start = "25/09/2024 14:00";
        String end = "25/09/2024 16:00";
        CreateCommand command = new CreateCommand("Team meeting", start, end, TaskType.EVENT);
        String result = command.execute(taskListStub, uiStub, storageStub);

        assertEquals(1, taskListStub.numOfTasks());
        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("Team meeting"));
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

    @Test
    public void testExecute_emptyDescription_throwsDookException() {
        CreateCommand command = new CreateCommand("", TaskType.TODO);

        DookException exception = assertThrows(DookException.class, () -> {
            command.execute(taskListStub, uiStub, storageStub);
        });

        assertEquals("Need a description for your task", exception.getMessage());
    }

    @Test
    public void testExecute_missingDeadlineByDate_throwsDookException() {
        CreateCommand command = new CreateCommand("Submit report", "", TaskType.DEADLINE);

        DookException exception = assertThrows(DookException.class, () -> {
            command.execute(taskListStub, uiStub, storageStub);
        });

        assertEquals("Need a due date for your deadline", exception.getMessage());
    }

    @Test
    public void testExecute_missingEventTimes_throwsDookException() {
        CreateCommand command = new CreateCommand("Team meeting", "", "", TaskType.EVENT);

        DookException exception = assertThrows(DookException.class, () -> {
            command.execute(taskListStub, uiStub, storageStub);
        });

        assertEquals("Need a start and end time for your event", exception.getMessage());
    }


}
