package arts.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents the AddTodoCommandTest class contains unit tests for the AddTodoCommand class.
 * It tests the functionality of adding todo tasks to a task list and ensures that exceptions
 * are correctly thrown for invalid inputs.
 */
public class AddTodoCommandTest {

    private TaskList tasks;
    private StubStorage storage;
    private StubUi ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the task list, and sets up storage and UI stubs.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList(new ArrayList<>());
        storage = new StubStorage("dummy/path/to/storage.txt");
        ui = new StubUi();
    }

    /**
     * Tests the successful addition of a todo task.
     * Verifies that the task list is updated correctly and the UI shows the correct message.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void execute_addValidTodo_success() throws ArtsException {
        String description = "Read a book";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);

        String result = command.execute();

        assertEquals(1, tasks.size(), "Task list should have one task after addition.");
        assertEquals("Hooray! 🎊 A new adventure awaits with this task:\n✨ [T][ ] Read a book ✨\n"
                + "Your quest now has 1 task to conquer! Keep shining, champion! 🌟", result);
    }


    /**
     * Tests that spaces in the description are normalized.
     * Verifies that the task description is trimmed and spaces are normalized.
     *
     * @throws ArtsException if an error occurs during command execution.
     */
    @Test
    public void execute_normalizeSpacesInDescription() throws ArtsException {
        String description = "   Read   a   book   ";
        AddTodoCommand command = new AddTodoCommand(tasks, storage, ui, description);
        command.execute();

        assertEquals("Read a book", tasks.getTask(0).getDescription(),
                "Task description should be normalized.");
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
