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
 * Represents the DeleteCommandTest class contains unit tests for the DeleteCommand class.
 * It tests the functionality of deleting tasks from a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class DeleteCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the task list with sample tasks, and sets up storage and UI stubs.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        tasks.addTask(new Todo("Read a book"));
        tasks.addTask(new Todo("Write a report"));
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    /**
     * Tests the successful deletion of a task.
     * Verifies that the task list is updated correctly and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void testDeleteTaskSuccessfully() throws ArtsException {
        String taskIndex = "1";
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

        String result = command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task after deletion.");
        assertEquals("Farewell, brave task! 🌸 You've been removed from the quest:\n💔 [T][ ] Read a book 💔\n"
                + "The journey continues with 1 task left. Keep going, warrior! 🗡️", result);
    }

    /**
     * Tests that an ArtsException is thrown when an invalid task index is provided.
     * Verifies that the exception message indicates the invalid task index.
     */
    @Test
    public void testInvalidTaskIndexThrowsException() {
        String taskIndex = "3"; // Out of bounds
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

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
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

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
     * Represents a stub class for Ui used in testing.
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
