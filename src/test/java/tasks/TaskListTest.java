package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.DukeException;

class TaskListTest {

    private static final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void resetStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testAddTask() throws DukeException {
        TaskList curr = TaskList.getInstance();
        String[] input = {"todo", "read book"};
        String expected = "Got it. I've added this task:\n" +
                "[T][ ] read book\n" +
                "Now you have 1 tasks in the list.\n";
        curr.createTask(input[0], input[1]);
        assertEquals(expected, outContent.toString());
        outContent.reset();
        input = new String[]{"deadline", "return book /by 2024-08-23 1200"};
        expected = "Got it. I've added this task:\n" +
                "[D][ ] return book (by: Aug 23 2024 12:00)\n" +
                "Now you have 2 tasks in the list.\n";
        curr.createTask(input[0], input[1]);
        assertEquals(expected, outContent.toString());
        outContent.reset();

        input = new String[]{"event", "project meeting /from 2024-08-23 1400 /to 2024-08-23 1600"};
        expected = "Got it. I've added this task:\n" +
                "[E][ ] project meeting (from: Aug 23 2024 14:00 to: Aug 23 2024 16:00)\n" +
                "Now you have 3 tasks in the list.\n";
        curr.createTask(input[0], input[1]);
        assertEquals(expected, outContent.toString());
        outContent.reset();
    }

    @Test
    void testDeleteTask() throws DukeException {
        TaskList curr = TaskList.getInstance();
        String expected = "Noted. I've removed this task:\n" +
                "[E][ ] project meeting (from: Aug 23 2024 14:00 to: Aug 23 2024 16:00)\n" +
                "Now you have 2 tasks in the list.";
        curr.deleteTask("3");
        assertEquals(expected, outContent.toString().trim());
        outContent.reset();

    }

    @Test
    void getTaskList() {
        TaskList curr = TaskList.getInstance();
        DukeException e = assertThrows(DukeException.class, () -> curr.deleteTask("3"));
        assertEquals("Invalid index provided.", e.getMessage());
    }
}