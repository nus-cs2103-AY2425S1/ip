package arts.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a class that contains unit tests for the AddDeadlineCommand class.
 * It tests the functionality of adding deadlines to a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class AddDeadlineCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;
    private DateTimeFormatter[] formatters;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the task list, storage, UI stubs, and date formatters.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
        formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };
    }

    /**
     * Tests the successful addition of a deadline task.
     * Verifies that the task list contains the new task and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void testAddDeadlineSuccessfully() throws ArtsException {
        String details = "Finish report /by 2024-08-29 1800";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        String result = command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Yatta! 🎉 I've successfully added this task to your list:\n✨ " + tasks.getTask(0)
                + " ✨\nNow your quest has 1 task to conquer! Ganbatte! 💪", result);
    }

    /**
     * Tests that an ArtsException is thrown when the deadline command is missing a /by date.
     * Verifies that the exception message indicates the missing /by date.
     */
    @Test
    public void testMissingByDateThrowsException() {
        String details = "Finish report";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Details must contain a '/by' to separate task description and deadline",
                exception.getMessage(),
                "Exception message should indicate missing /by date.");
    }

    /**
     * Tests that an ArtsException is thrown for an invalid date format in the deadline command.
     * Verifies that the exception message indicates the invalid date format.
     */
    @Test
    public void testInvalidDateFormatThrowsException() {
        String details = "Finish report /by 29-08-2024 1800";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.",
                exception.getMessage(), "Exception message should indicate invalid date format.");
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
