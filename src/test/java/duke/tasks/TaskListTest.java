package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;

/**
 * Test class for the TaskList class.
 */
class TaskListTest {

    private static final PrintStream sysOut = System.out;
    private static final PrintStream sysErr = System.err;

    private static final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream newErr = new ByteArrayOutputStream();

    /**
     * Sets up the output streams for testing.
     */
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(newOut));
        System.setErr(new PrintStream(newErr));
        Storage.startTest();
        TaskList.getInstance().clearTasks();
    }

    /**
     * Resets the output streams after testing.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(sysOut);
        System.setErr(sysErr);
        Storage.endTest();
        newOut.reset();
        newErr.reset();
    }

    /**
     * Tests adding different types of tasks to the TaskList.
     */
    @Test
    void testAddTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        String[] input = {"todo", "read book"};
        String expected = "Got it. I've added this task:\n"
                + "[T][ ] read book\n"
                + "Now you have 1 tasks in the list.\n";
        taskList.createTask(input[0], input[1]);
        assertEquals(expected, newOut.toString());
        newOut.reset();
        input = new String[]{"deadline", "return book /by 2024-08-23 1200"};
        expected = "Got it. I've added this task:\n"
                + "[D][ ] return book (by: Aug 23 2024 12:00)\n"
                + "Now you have 2 tasks in the list.\n";
        taskList.createTask(input[0], input[1]);
        assertEquals(expected, newOut.toString());
        newOut.reset();

        input = new String[]{"event", "project meeting /from 2024-08-23 1400 /to 2024-08-23 1600"};
        expected = "Got it. I've added this task:\n"
                + "[E][ ] project meeting (from: Aug 23 2024 14:00 to: Aug 23 2024 16:00)\n"
                + "Now you have 3 tasks in the list.\n";
        taskList.createTask(input[0], input[1]);
        assertEquals(expected, newOut.toString());
        newOut.reset();
    }

    /**
     * Tests deleting a task from the TaskList.
     */
    @Test
    void testDeleteTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "task to delete");
        newOut.reset();
        String expected = "Noted. I've removed this task:\n"
                + "[T][ ] task to delete\n"
                + "Now you have 0 tasks in the list.";
        taskList.deleteTask("1");
        assertEquals(expected, newOut.toString().trim());
    }

    /**
     * Tests the behavior when trying to delete a task with an invalid index.
     */
    @Test
    void testDeleteInvalidTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        DukeException e = assertThrows(DukeException.class, () -> taskList.deleteTask("1"));
        assertEquals("Task list is already empty.", e.getMessage());
        taskList.createTask("todo", "test task");
        newOut.reset();
        e = assertThrows(DukeException.class, () -> taskList.deleteTask("2"));
        assertEquals("Invalid index provided.", e.getMessage());
    }
}
