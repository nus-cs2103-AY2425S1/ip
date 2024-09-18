package arts.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.task.Todo;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents the UnmarkCommandTest class contains unit tests for the UnmarkCommand class.
 * It tests the functionality of unmarking tasks as not done in a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class UnmarkCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the task list with a sample task marked as done, and sets up storage and UI stubs.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        Todo task = new Todo("Read a book");
        task.markAsDone();
        tasks.addTask(task);
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    /**
     * Tests the successful unmarking of a task as not done.
     * Verifies that the task's status is updated correctly and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void testUnmarkTaskSuccessfully() throws ArtsException {
        String taskIndex = "1";
        UnmarkCommand command = new UnmarkCommand(tasks, storage, ui, taskIndex);

        String result = command.execute();

        assertEquals(false, tasks.getTask(0).isDone(), "Task should be unmarked as not done.");
        assertEquals("🎌 Fear not, for this task has been unmarked! 🗒️\n"
                + "Continue your quest with renewed vigor, valiant warrior! 🌟\n [T][ ] Read a book", result);
    }

    /**
     * Tests that an ArtsException is thrown when an invalid task index is provided.
     * Verifies that the exception message indicates the task index is out of bounds.
     */
    @Test
    public void testInvalidTaskIndexThrowsException() {
        String taskIndex = "2"; // Out of bounds
        UnmarkCommand command = new UnmarkCommand(tasks, storage, ui, taskIndex);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Task index is out of bounds.", exception.getMessage(),
                "Exception message should indicate task index is out of bounds.");
    }

    /**
     * Tests that an ArtsException is thrown when a non-numeric task index is provided.
     * Verifies that the exception message indicates the task index is not a number.
     */
    @Test
    public void testNonNumericTaskIndexThrowsException() {
        String taskIndex = "a"; // Non-numeric
        UnmarkCommand command = new UnmarkCommand(tasks, storage, ui, taskIndex);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Task index must be a number.", exception.getMessage(),
                "Exception message should indicate task index is not a number.");
    }

    /**
     * Represents a stub class for Storage used in testing.
     * Overrides the save method to do nothing, simulating a storage component without actual file operations.
     */
    private static class StubStorage extends Storage {
        public StubStorage(String filePath) {
            super(filePath);
        }

        @Override
        public void save(ArrayList<Task> tasks) {
            // Do nothing
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
