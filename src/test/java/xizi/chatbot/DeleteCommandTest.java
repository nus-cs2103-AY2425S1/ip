package xizi.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xizi.chatbot.command.DeleteCommand;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link DeleteCommand} class.
 * This class tests the functionality related to deleting tasks from the {@link TaskList}.
 */
public class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the necessary objects before each test.
     * Initializes an empty {@link TaskList}, a {@link Ui}, and a {@link Storage} object with the file path "testData.txt".
     * Adds a default task to the task list for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("testData.txt");
        tasks.addTask(new Todo("read book"));
    }

    /**
     * Tests the functionality of the {@link DeleteCommand} to delete a task.
     * Verifies that the task is correctly removed from the task list.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testDeleteCommand() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        deleteCommand.execute(tasks, storage, ui);

        assertEquals(0, tasks.getSize());
    }

    /**
     * Tests the behavior when attempting to delete a task with an invalid index.
     * Verifies that an exception is thrown with the appropriate error message when the task index does not exist.
     */
    @Test
    public void testDeleteInvalidTask() {
        Exception exception = assertThrows(Exception.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand("delete 2");
            deleteCommand.execute(tasks, storage, ui);
        });

        assertTrue(exception.getMessage().contains("The task number does not exist."));
    }
}

