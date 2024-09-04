package momo.command;

import momo.exception.InvalidCommandException;
import momo.StorageStub;
import momo.task.TasklistStub;
import momo.task.Todo;
import momo.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DeleteCommandTest {
    TasklistStub tasks;
    StorageStub storage;

    Task task1;
    Task task2;
    Task task3;

    @BeforeEach
    void setUp() {
        tasks = new TasklistStub("");
        storage = new StorageStub("");

        task1 = new Todo("sample test1", false);
        task2 = new Todo("sample test2", false);
        task3 = new Todo("sample test3", false);

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

    }

    @Test
    void testValidDeleteCommand() throws Exception {
        DeleteCommand.run("delete 1", tasks, storage);  // Deletes "sample test1"
        assertEquals(2, tasks.getCount(), "Task count should be 2 after deleting the first task");

        DeleteCommand.run("delete 2", tasks, storage);  // Deletes "sample test3" (now at index 1)
        assertEquals(1, tasks.getCount(), "Task count should be 1 after deleting the second task");

        assertTrue(tasks.getTaskList().contains(task2));
        assertFalse(tasks.getTaskList().contains(task1));
        assertFalse(tasks.getTaskList().contains(task3));
    }

    @Test
    void testInvalidDeleteCommand() throws Exception {
        Exception noNumberException = assertThrows(InvalidCommandException.class, () -> DeleteCommand.run("delete eowid", tasks, storage));
        String expectedMessage = "Watch out: You did not format your number properly...";
        assertTrue(noNumberException.getMessage().contains(expectedMessage));

        Exception numberNotInListException = assertThrows(InvalidCommandException.class, () -> DeleteCommand.run("delete 4", tasks, storage));
        expectedMessage = "You can only delete a number your task list contains";
        assertTrue(numberNotInListException.getMessage().contains(expectedMessage));


    }


}
