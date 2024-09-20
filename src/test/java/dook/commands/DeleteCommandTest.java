package dook.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dook.DookException;
import dook.tasks.Event;
import dook.tasks.Task;
import dook.tasks.TaskType;
import dook.tasks.Todo;

public class DeleteCommandTest {

    private TaskListStub taskListStub = new TaskListStub(new ArrayList<Task>());
    private UiStub uiStub = new UiStub();
    private StorageStub storageStub = new StorageStub("filePath");

    @Test
    public void deleteTasks_execute_success() throws DookException, IOException {
        Todo deleteFirst = new Todo("Eat");
        Event deleteNext = new Event("Meeting", LocalDateTime.now(), LocalDateTime.now());

        taskListStub.add(deleteFirst);
        taskListStub.add(deleteNext);

        DeleteCommand toDeleteFirst = new DeleteCommand(1);
        toDeleteFirst.execute(taskListStub, uiStub, storageStub);

        assertEquals(1, taskListStub.numOfTasks());
        assertEquals(deleteNext, taskListStub.getTask(0));

        DeleteCommand toDeleteSecond = new DeleteCommand(1);
        toDeleteSecond.execute(taskListStub, uiStub, storageStub);

        assertEquals(0, taskListStub.numOfTasks());
    }

    @Test
    public void testExecute_deleteNegativeTaskNumber_throwsDookException() {
        DeleteCommand command = new DeleteCommand(-1);
        DookException exception = assertThrows(DookException.class, () -> {
            command.execute(taskListStub, uiStub, storageStub);
        });

        assertEquals("You don't have that many tasks", exception.getMessage());
    }

    @Test
    public void deleteTasks_execute_exceptionThrown() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(3);
            deleteCommand.execute(new TaskListStub(new ArrayList<Task>()), uiStub, storageStub);
        } catch (Exception e) {
            assertEquals("You don't have that many tasks", e.getMessage());
        }
    }

    @Test
    public void testExecute_invalidEventEndBeforeStart_throwsDookException() {
        String start = "25/09/2024 16:00";
        String end = "25/09/2024 14:00";
        CreateCommand command = new CreateCommand("Team meeting", start, end, TaskType.EVENT);

        DookException exception = assertThrows(DookException.class, () -> {
            command.execute(taskListStub, uiStub, storageStub);
        });

        assertEquals("Start time cannot be after end time", exception.getMessage());
    }
}
