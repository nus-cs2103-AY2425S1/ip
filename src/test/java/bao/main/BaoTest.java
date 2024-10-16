package bao.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bao.task.Deadline;
import bao.task.Event;
import bao.task.Task;
import bao.task.ToDo;

public class BaoTest {
    public static final String TEST_FILE_PATH = "data/test_bao.json.txt";
    private Bao bao;

    /**
     * Sets up a test file with known content.
     */
    @BeforeEach
    public void setUp() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.println("T | 0 | do laundry");
            writer.println("D | 1 | cs2103t ip | 2024-08-29 1600");
            writer.println("E | 0 | block event | 2024-08-27 2100 - 2024-08-27 2300");
        } catch (IOException e) {
            e.printStackTrace();
        }

        bao = new Bao(TEST_FILE_PATH);
    }

    /**
     * Tests if file is initialised correctly.
     */
    @Test
    public void initialiseValidFileTest() {
        assertEquals(3, bao.getTaskList().size());

        Task task1 = bao.getTaskList().getTask(0);
        assertInstanceOf(ToDo.class, task1);
        assertEquals("do laundry", task1.getDescription());
        assertFalse(task1.isDone());

        Task task2 = bao.getTaskList().getTask(1);
        assertInstanceOf(Deadline.class, task2);
        assertEquals("cs2103t ip", task2.getDescription());
        assertTrue(task2.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 29, 16, 00), (
                (Deadline) task2).getDate());

        Task task3 = bao.getTaskList().getTask(2);
        assertInstanceOf(Event.class, task3);
        assertEquals("block event", task3.getDescription());
        assertFalse(task3.isDone());
        assertEquals(LocalDateTime.of(2024, 8, 27, 21, 00), (
                (Event) task3).getFromDateTime());
        assertEquals(LocalDateTime.of(2024, 8, 27, 23, 00), (
                (Event) task3).getToDateTime());
    }
}
