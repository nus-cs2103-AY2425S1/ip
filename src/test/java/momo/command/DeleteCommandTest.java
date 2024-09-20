package momo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import momo.StorageStub;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TasklistStub;
import momo.task.Todo;


public class DeleteCommandTest {
    private TasklistStub tasks;
    private StorageStub storage;

    private Task sampleTaskOne;
    private Task sampleTaskTwo;
    private Task sampleTaskThree;

    @BeforeEach
    void setUp() {
        tasks = new TasklistStub("");
        storage = new StorageStub("");

        sampleTaskOne = new Todo("sample test 1", false);
        sampleTaskTwo = new Todo("sample test 2", false);
        sampleTaskThree = new Todo("sample test 3", false);

        tasks.addTask(sampleTaskOne);
        tasks.addTask(sampleTaskTwo);
        tasks.addTask(sampleTaskThree);
    }

    @Test
    void testValidDeleteCommand() throws Exception {
        DeleteCommand.run("delete 1", tasks, storage); // Deletes "sample test1"
        assertEquals(2, tasks.getCount(), "Task count should be 2 after deleting the first task");

        DeleteCommand.run("delete 2", tasks, storage); // Deletes "sample test3" (now at index 1)
        assertEquals(1, tasks.getCount(), "Task count should be 1 after deleting the second task");

        assertTrue(tasks.getTaskList().contains(sampleTaskTwo));
        assertFalse(tasks.getTaskList().contains(sampleTaskOne));
        assertFalse(tasks.getTaskList().contains(sampleTaskThree));
    }

    @Test
    void testInvalidDeleteCommand() throws Exception {
        Exception noNumberException = assertThrows(InvalidCommandException.class, () ->
                DeleteCommand.run("delete eowid", tasks, storage));
        String expectedMessage = "Watch out: You did not format your number properly...";
        assertTrue(noNumberException.getMessage().contains(expectedMessage));

        Exception numberNotInListException = assertThrows(InvalidCommandException.class, () ->
                DeleteCommand.run("delete 4", tasks, storage));
        expectedMessage = "You can only delete a number your task list contains";
        assertTrue(numberNotInListException.getMessage().contains(expectedMessage));
    }
}
