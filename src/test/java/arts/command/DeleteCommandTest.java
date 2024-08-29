package arts.command;

import arts.task.TaskList;
import arts.task.Task;
import arts.task.Todo;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        tasks.addTask(new Todo("Read a book"));
        tasks.addTask(new Todo("Write a report"));
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    @Test
    public void testDeleteTaskSuccessfully() throws ArtsException {
        String taskIndex = "1";
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

        command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task after deletion.");
        assertEquals("Noted. I've removed this task:\n [T][ ] Read a book\nNow you have 1 task in the list.", ui.getLastMessage());
    }

    @Test
    public void testInvalidTaskIndexThrowsException() {
        String taskIndex = "3"; // Out of bounds
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid task index.", exception.getMessage(), "Exception message should indicate invalid task index.");
    }

    @Test
    public void testNonNumericTaskIndexThrowsException() {
        String taskIndex = "a"; // Non-numeric
        DeleteCommand command = new DeleteCommand(tasks, storage, ui, taskIndex);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("Invalid task index.", exception.getMessage(), "Exception message should indicate invalid task index.");
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
