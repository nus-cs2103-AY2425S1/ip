package arts.command;

import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;
import arts.task.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTodoCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    @Test
    public void testAddTodoSuccessfully() throws ArtsException {
        String description = "Read a book";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task.");
        assertEquals("Got it. I've added this task:\n " + tasks.getTask(0) +
                "\nNow you have 1 task in the list.", ui.getLastMessage());
    }

    @Test
    public void testEmptyDescriptionThrowsException() {
        String description = "";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The description of a todo cannot be empty.", exception.getMessage(), "Exception message should indicate empty description.");
    }

    @Test
    public void testNullDescriptionThrowsException() {
        String description = null;
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        ArtsException exception = assertThrows(ArtsException.class, command::execute);
        assertEquals("The description of a todo cannot be empty.", exception.getMessage(), "Exception message should indicate empty description.");
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