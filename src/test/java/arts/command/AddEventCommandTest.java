package arts.command;

import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;
import arts.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEventCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;
    private DateTimeFormatter[] formatters;

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

    @Test
    public void testAddEventSuccessfully() throws ArtsException {
        String details = "Conference /from 2024-08-29 0900 /to 2024-08-29 1700";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Got it. I've added this task:\n " + tasks.getTask(0) +
                "\nNow you have 1 task in the list.", ui.getLastMessage());
    }

    @Test
    public void testMissingFromOrToThrowsException() {
        String details = "Conference /from 2024-08-29 0900";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The event must have /from and /to times.", exception.getMessage(), "Exception message should indicate missing /from or /to times.");
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        String details = "Conference /from 29-08-2024 0900 /to 29-08-2024 1700";
        AddEventCommand command = new AddEventCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.", exception.getMessage(), "Exception message should indicate invalid date format.");
    }

    // Stub classes for Storage and Ui
    private static class StubStorage extends Storage {
        public StubStorage(String filePath) {
            super(filePath);
        }

        @Override
        public void save(ArrayList<Task> tasks) throws ArtsException {
            // Do nothing for now
        }
    }

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