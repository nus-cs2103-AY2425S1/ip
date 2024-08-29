package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * Tests marking a task as done.
     */
    @Test
    void testMarkTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "task to mark");
        newOut.reset();
        taskList.mark("1");
        String expected = "Nice! I've marked this task as done:\n"
                + "[T][X] task to mark";
        assertTrue(newOut.toString().trim().contains(expected));
    }

    /**
     * Tests unmarking a task.
     */
    @Test
    void testUnmarkTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "task to unmark");
        taskList.mark("1");
        newOut.reset();
        taskList.unmark("1");
        String expected = "Ok, I've marked this task as not done yet:\n"
                + "[T][ ] task to unmark";
        assertTrue(newOut.toString().trim().contains(expected));
    }

    /**
     * Tests listing tasks when the list is empty.
     */
    @Test
    void testEmptyList() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.printTaskList();
        assertEquals("List is currently empty.", newOut.toString().trim());
    }

    /**
     * Tests listing tasks when the list is not empty.
     */
    @Test
    void testNonEmptyList() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "test task");
        newOut.reset();
        taskList.printTaskList();
        assertTrue(newOut.toString().contains("Here are the tasks in your list:\n1. [T][ ] test task"));
    }

    /**
     * Tests finding tasks based on a keyword.
     */
    @Test
    void testFindTask() throws DukeException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "read book");
        taskList.createTask("todo", "write report");
        newOut.reset();
        taskList.filter("book");
        assertTrue(newOut.toString().contains("Here are the matching tasks in your list:"));
        assertTrue(newOut.toString().contains("[T][ ] read book"));
        assertTrue(!newOut.toString().contains("write report"));
    }

    /**
     * Tests the behavior when trying to mark a task with an invalid index.
     */
    @Test
    void testMarkInvalidTask() {
        TaskList taskList = TaskList.getInstance();
        DukeException e = assertThrows(DukeException.class, () -> taskList.mark("1"));
        assertEquals("List is empty, no tasks to mark.", e.getMessage());
    }

    /**
     * Tests the behavior when trying to unmark a task with an invalid index.
     */
    @Test
    void testUnmarkInvalidTask() {
        TaskList taskList = TaskList.getInstance();
        DukeException e = assertThrows(DukeException.class, () -> taskList.unmark("1"));
        assertEquals("List is empty, no tasks to unmark.", e.getMessage());
    }
}
