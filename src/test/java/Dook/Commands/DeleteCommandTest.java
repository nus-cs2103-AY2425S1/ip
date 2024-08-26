package Dook.Commands;

import Dook.DookException;
import Dook.Storage.Storage;
import Dook.Tasks.Event;
import Dook.Tasks.Task;
import Dook.Tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void deleteTasks_execute_exceptionThrown() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(3);
            deleteCommand.execute(new TaskListStub(new ArrayList<Task>()), uiStub, storageStub);
        } catch (Exception e) {
            assertEquals("You don't have that many tasks", e.getMessage());
        }
    }
}
