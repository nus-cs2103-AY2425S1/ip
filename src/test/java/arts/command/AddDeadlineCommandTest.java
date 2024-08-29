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

public class AddDeadlineCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;
    private DateTimeFormatter[] formatters;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new StubStorage("dummy/path/to/storage.txt"); // Provide a dummy path
        ui = new StubUi();
        formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };
    }

    @Test
    public void testAddDeadlineSuccessfully() throws ArtsException {
        String details = "Finish report /by 2024-08-29 1800";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Got it. I've added this task:\n " + tasks.getTask(0) +
                "\nNow you have 1 task in the list.", ui.getLastMessage());
    }

    @Test
    public void testMissingByDateThrowsException() {
        String details = "Finish report";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The deadline must have a /by date.", exception.getMessage(), "Exception message should indicate missing /by date.");
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        String details = "Finish report /by 29-08-2024 1800";
        AddDeadlineCommand command = new AddDeadlineCommand(tasks, storage, ui, details, formatters);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.", exception.getMessage(), "Exception message should indicate invalid date format.");
    }

    // Stub classes for Storage and Ui
    private static class StubStorage extends Storage {
        public StubStorage(String filePath) {
            super(filePath); // Use the constructor from Storage
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