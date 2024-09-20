package sadcat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sadcat.exceptions.SadCatException;
import sadcat.storage.Storage;

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
    void testAddTask() throws SadCatException {
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
    void testDeleteTask() throws SadCatException {
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
    void testDeleteInvalidTask() throws SadCatException {
        TaskList taskList = TaskList.getInstance();
        SadCatException e = assertThrows(SadCatException.class, () -> taskList.deleteTask("1"));
        assertEquals("Task list is already empty.", e.getMessage());
        taskList.createTask("todo", "test task");
        newOut.reset();
        e = assertThrows(SadCatException.class, () -> taskList.deleteTask("2"));
        assertEquals("Invalid index provided.", e.getMessage());
    }

    /**
     * Tests marking a task as done.
     */
    @Test
    void testMarkTask() throws SadCatException {
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
    void testUnmarkTask() throws SadCatException {
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
    void testEmptyList() throws SadCatException {
        TaskList taskList = TaskList.getInstance();
        taskList.printTaskList();
        assertEquals("List is currently empty.", newOut.toString().trim());
    }

    /**
     * Tests listing tasks when the list is not empty.
     */
    @Test
    void testNonEmptyList() throws SadCatException {
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
    void testFindTask() throws SadCatException {
        TaskList taskList = TaskList.getInstance();
        taskList.createTask("todo", "read book");
        taskList.createTask("todo", "write report");
        newOut.reset();
        taskList.filter("book");
        assertTrue(newOut.toString().contains("Here are the matching tasks in your list:"));
        assertTrue(newOut.toString().contains("[T][ ] read book"));
        assertFalse(newOut.toString().contains("write report"));
    }

    /**
     * Tests the behavior when trying to mark a task with an invalid index.
     */
    @Test
    void testMarkInvalidTask() {
        TaskList taskList = TaskList.getInstance();
        SadCatException e = assertThrows(SadCatException.class, () -> taskList.mark("1"));
        assertEquals("List is empty, no tasks to mark.", e.getMessage());
    }

    /**
     * Tests the behavior when trying to unmark a task with an invalid index.
     */
    @Test
    void testUnmarkInvalidTask() {
        TaskList taskList = TaskList.getInstance();
        SadCatException e = assertThrows(SadCatException.class, () -> taskList.unmark("1"));
        assertEquals("List is empty, no tasks to unmark.", e.getMessage());
    }

    /**
     * Tests updating tasks in the TaskList.
     */
    @Test
    void testUpdateTask() throws SadCatException {
        TaskList taskList = TaskList.getInstance();

        taskList.createTask("todo", "read book");
        taskList.createTask("deadline", "return book /by 2024-08-23 1200");
        taskList.createTask("event", "project meeting /from 2024-08-23 1400 /to 2024-08-23 1600");
        newOut.reset();

        taskList.updateTask("1 deadline finish book /by 2024-09-01 1800");
        String expected = "Got it. I've updated the task:\n"
                + "[D][ ] finish book (by: Sept 1 2024 18:00)";
        assertTrue(newOut.toString().contains(expected));
        newOut.reset();

        taskList.updateTask("2 event book return /from 2024-08-23 1100 /to 2024-08-23 1300");
        expected = "Got it. I've updated the task:\n"
                + "[E][ ] book return (from: Aug 23 2024 11:00 to: Aug 23 2024 13:00)";
        assertTrue(newOut.toString().trim().contains(expected));
        newOut.reset();

        taskList.updateTask("3 todo reschedule project meeting");
        expected = "Got it. I've updated the task:\n"
                + "[T][ ] reschedule project meeting";
        assertTrue(newOut.toString().trim().contains(expected));
        newOut.reset();

        SadCatException e = assertThrows(SadCatException.class, () -> taskList.updateTask("4 todo invalid task"));
        assertEquals("Task index out of range.", e.getMessage());

        e = assertThrows(SadCatException.class, () -> taskList.updateTask("1 invalid_type some task"));
        assertEquals("Invalid task type.", e.getMessage());

        e = assertThrows(SadCatException.class, () -> taskList.updateTask(
                "1 deadline invalid date /by 2024-13-45 2500"));
        assertEquals("Invalid date format.", e.getMessage());

        e = assertThrows(SadCatException.class, () -> taskList.updateTask("1 deadline invalid format"));
        assertEquals("Invalid deadline format.", e.getMessage());

        e = assertThrows(SadCatException.class, () -> taskList.updateTask(
                "1 event invalid format /from 2024-08-23 1400"));
        assertEquals("Invalid event format.", e.getMessage());

        newOut.reset();
        taskList.printTaskList();
        String finalList = newOut.toString().trim();
        assertTrue(finalList.contains("[D][ ] finish book (by: Sept 1 2024 18:00)"));
        assertTrue(finalList.contains("[E][ ] book return (from: Aug 23 2024 11:00 to: Aug 23 2024 13:00)"));
        assertTrue(finalList.contains("[T][ ] reschedule project meeting"));
    }

    /**
     * Tests the file switching functionality.
     */
    @Test
    void testFileSwitching() throws SadCatException {
        TaskList taskList = TaskList.getInstance();
        Storage storage = Storage.getInstance();

        // Add a task to the default file
        taskList.createTask("todo", "Default file task");
        newOut.reset();

        // Switch to a new file
        storage.changeFile("newfile");
        taskList.printTaskList();
        assertEquals("Switched to file: newfile_test.txt\n" + "List is currently empty.", newOut.toString().trim());
        newOut.reset();

        // Add a task to the new file
        taskList.createTask("todo", "New file task");
        newOut.reset();

        // Switch back to the default file
        storage.changeFile("sadcat");
        taskList.printTaskList();
        assertTrue(newOut.toString().contains("1. [T][ ] Default file task"));
        assertFalse(newOut.toString().contains("New file task"));
        newOut.reset();

        // Switch back to the new file
        storage.changeFile("newfile");
        taskList.printTaskList();
        assertTrue(newOut.toString().contains("1. [T][ ] New file task"));
        assertFalse(newOut.toString().contains("Default file task"));
        newOut.reset();

        // Add another task to the new file
        taskList.createTask("deadline", "New file deadline /by 2024-08-23 1200");
        newOut.reset();

        // Switch to the default file and verify it's unchanged
        storage.changeFile("sadcat");
        taskList.printTaskList();
        assertTrue(newOut.toString().contains("1. [T][ ] Default file task"));
        assertFalse(newOut.toString().contains("New file task"));
        assertFalse(newOut.toString().contains("New file deadline"));
        newOut.reset();

        // Switch back to the new file and verify both tasks are there
        storage.changeFile("newfile");
        taskList.printTaskList();
        assertTrue(newOut.toString().contains("1. [T][ ] New file task"));
        assertTrue(newOut.toString().contains("2. [D][ ] New file deadline (by: Aug 23 2024 12:00)"));
        assertFalse(newOut.toString().contains("Default file task"));
    }
}
