package bao.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bao.main.Bao;

public class TaskTest {
    public static final String TEST_FILE_PATH = "data/test_task.json.txt";
    private Bao bao;

    /**
     * Sets up a test file with known content.
     */
    @BeforeEach
    public void setUp() {
        Bao bao = new Bao(TEST_FILE_PATH);
    }

    /**
     * Tests if todo task is created correctly using fromString method.
     */
    @Test
    public void createToDoTaskUsingFromStringTest() {
        String input = "T | 0 | do laundry";
        Task task = Task.fromString(input);
        assertInstanceOf(ToDo.class, task);
        assertEquals("do laundry", task.getDescription());
        assertFalse(task.isDone());

        input = "T | 1 | do laundry";
        task = Task.fromString(input);
        assertInstanceOf(ToDo.class, task);
        assertTrue(task.isDone());
    }

    /**
     * Tests if deadline task is created correctly using fromString method.
     */
    @Test
    public void createDeadlineTaskUsingFromStringTest() {
        String input = "D | 0 | cs2103t ip | 2024-08-29 1600";
        Task task = Task.fromString(input);
        assertInstanceOf(Deadline.class, task);
        assertEquals("cs2103t ip", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 29, 16, 00), (
                (Deadline) task).getDate());

        input = "D | 1 | cs2103t ip | 2024-08-29 1600";
        task = Task.fromString(input);
        assertInstanceOf(Deadline.class, task);
        assertEquals("cs2103t ip", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 29, 16, 00), (
                (Deadline) task).getDate());
    }

    /**
     * Tests if event task is created correctly using fromString method.
     */
    @Test
    public void createEventTaskUsingFromStringTest() {
        String input = "E | 0 | block event | 2024-08-27 2100 - 2024-08-27 2300";
        Task task = Task.fromString(input);
        assertInstanceOf(Event.class, task);
        assertEquals("block event", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 27, 21, 00), (
                (Event) task).getFromDateTime());
        assertEquals(LocalDateTime.of(2024, 8, 27, 23, 00), (
                (Event) task).getToDateTime());

        input = "E | 1 | block event | 2024-08-27 2100 - 2024-08-27 2300";
        task = Task.fromString(input);
        assertInstanceOf(Event.class, task);
        assertEquals("block event", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 27, 21, 00), (
                (Event) task).getFromDateTime());
        assertEquals(LocalDateTime.of(2024, 8, 27, 23, 00), (
                (Event) task).getToDateTime());
    }

    /**
     * Tests mark and unmark methods.
     */
    @Test
    public void markTest() {
        Task task = new ToDo("do laundry");
        assertFalse(task.isDone());

        task.mark();
        assertTrue(task.isDone());

        task.unmark();
        assertFalse(task.isDone());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void toStringTest() {
        Task task = new ToDo("do laundry");
        assertEquals("T | 0 | do laundry", task.toString());

        task.mark();
        assertEquals("T | 1 | do laundry", task.toString());

        task = new ToDo("         do laundry        ");
        assertEquals("T | 0 | do laundry", task.toString());
    }

    /**
     * Tests toFileString method.
     */
    @Test
    public void toFileStringTest() {
        Task task = new ToDo("do laundry");
        assertEquals("T | 0 | do laundry", task.toFileString());

        task = new Deadline("cs2103t ip", LocalDateTime.of(2024, 8, 29, 16, 00));
        assertEquals("D | 0 | cs2103t ip | 2024-08-29 1600", task.toFileString());

        task = new Event("block event",
                LocalDateTime.of(2024, 8, 27, 21, 00),
                LocalDateTime.of(2024, 8, 27, 23, 00));
        assertEquals("E | 0 | block event | 2024-08-27 2100 - 2024-08-27 2300", task.toFileString());
    }
}
