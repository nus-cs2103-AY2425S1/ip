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
 * Represents the AddEventCommandTest class contains unit tests for the AddEventCommand class.
 * It tests the functionality of adding events to a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class AddEventCommandTest {

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
     * Tests the successful addition of an event task.
     * Verifies that the task list contains the new task and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void testAddEventSuccessfully() throws ArtsException {
        String details = "Conference /from 2024-08-29 0900 /to 2024-08-29 1700";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        String result = command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Sugoi! 🌟 I've added this epic event to your adventure:"
                        + "\n🎉 " + tasks.getTask(0)
                        + " 🎉\nNow your journey includes 1 task to tackle! Keep up the great work, hero! 💪",
                result);
    }

    /**
     * Tests that an ArtsException is thrown when the event command is missing /from or /to times.
     * Verifies that the exception message indicates the missing /from or /to times.
     */
    @Test
    public void testMissingFromOrToThrowsException() {
        String details = "Conference /from 2024-08-29 0900";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The event must have /from and /to times.", exception.getMessage(),
                "Exception message should indicate missing /from or /to times.");
    }

    /**
     * Tests that an ArtsException is thrown for an invalid date format in the event command.
     * Verifies that the exception message indicates the invalid date format.
     */
    @Test
    public void testInvalidDateFormatThrowsException() {
        String details = "Conference /from 29-08-2024 0900 /to 29-08-2024 1700";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.",
                exception.getMessage(), "Exception message should indicate invalid date format.");
    }

    /**
     * Tests that an ArtsException is thrown when the event start date is not before the end date.
     * Verifies that the exception message indicates the invalid event times.
     */
    @Test
    public void testInvalidEventTimesThrowsException() {
        String details = "Conference /from 2024-08-29 1700 /to 2024-08-29 0900";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Event start date must be before end date.", exception.getMessage(),
                "Exception message should indicate invalid event times.");
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
