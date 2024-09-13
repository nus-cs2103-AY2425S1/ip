package arts.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * The AddTodoCommandTest class contains unit tests for the AddTodoCommand class.
 * It tests the functionality of adding todo tasks to a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class AddTodoCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the task list, storage, and UI stubs.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    /**
     * Tests the successful addition of a todo task.
     * Verifies that the task list contains the new task and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void testAddTodoSuccessfully() throws ArtsException {
        String description = "Read a book";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Got it. I've added this task:\n " + tasks.getTask(0)
                + "\nNow you have 1 task in the list.", ui.getLastMessage());
    }

    /**
     * Tests that an ArtsException is thrown when the todo command has an empty description.
     * Verifies that the exception message indicates the empty description.
     */
    @Test
    public void testEmptyDescriptionThrowsException() {
        String description = "";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The description of a todo cannot be empty.", exception.getMessage(),
                "Exception message should indicate empty description.");
    }

    /**
     * Tests that an ArtsException is thrown when the todo command has a null description.
     * Verifies that the exception message indicates the empty description.
     */
    @Test
    public void testNullDescriptionThrowsException() {
        String description = null;
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The description of a todo cannot be empty.", exception.getMessage(),
                "Exception message should indicate empty description.");
    }

    /**
     * A stub class for Storage used in testing.
     * Overrides the save method to do nothing, simulating a storage component without actual file operations.
     */
    private static class StubStorage extends Storage {
        public StubStorage(String filePath) {
            super(filePath);
        }

        @Override
        public void save(ArrayList<Task> tasks) throws ArtsException {
            // Do nothing for now
        }
    }

    /**
     * A stub class for Ui used in testing.
     * Captures the last message shown by the UI for verification in tests.
     */
    private static class StubUi extends Ui {
        private String lastMessage;

        @Override
        public void showMessage(String message) {
            this.lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
