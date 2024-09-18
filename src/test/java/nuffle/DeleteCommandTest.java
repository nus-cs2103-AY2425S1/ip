package nuffle;

import nuffle.command.DeleteCommand;
import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.task.Todo;
import nuffle.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    void testDeleteCommandValidIndex() {
        // Adding sample tasks
        tasks.addTask(new Todo("Buy milk"));
        tasks.addTask(new Todo("Submit assignment"));

        // Test DeleteCommand for a valid index (delete the first task)
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        String result = deleteCommand.execute(tasks, storage, ui);

        // Check that the correct task is deleted
        assertTrue(result.contains("Noted. I've removed this task:"), "Should delete 'Buy milk'");
        assertEquals(1, tasks.getSize(), "Task list size should be 1 after deletion");
    }

    @Test
    void testDeleteCommandInvalidIndex() {
        // Adding sample tasks
        tasks.addTask(new Todo("Buy milk"));

        // Test DeleteCommand with an out-of-range index (delete the second task that does not exist)
        DeleteCommand deleteCommand = new DeleteCommand("delete 2");
        String result = deleteCommand.execute(tasks, storage, ui);

        // Check that an error message is returned
        assertTrue(result.contains("The index provided seems to be out of range."), "Should return an error for invalid index");
        assertEquals(1, tasks.getSize(), "Task list size should remain unchanged");
    }

    @Test
    void testDeleteCommandFromEmptyList() {
        // Test DeleteCommand on an empty task list
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        String result = deleteCommand.execute(tasks, storage, ui);

        // Check that an error message is returned
        assertTrue(result.contains("The index provided seems to be out of range."), "Should return an error for deletion from empty list");
        assertEquals(0, tasks.getSize(), "Task list size should remain 0");
    }
}
