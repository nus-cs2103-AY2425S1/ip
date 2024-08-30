package dude.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 9, 1, 10, 56);
        deadline = new Deadline("task 1", deadlineDateTime);
    }

    @Test
    public void testConstructor() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(1111, 11, 11, 11, 11);
        deadline = new Deadline("task 2", deadlineDateTime);

        assertEquals("task 2", deadline.description);
        assertEquals(LocalDateTime.of(1111, 11, 11, 11, 11), deadline.by);
        assertFalse(deadline.isDone);
    }

    @Test
    public void testMarkAsDone() {
        deadline.markAsDone();

        assertTrue(deadline.isDone);
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void testMarkAsNotDone() {
        deadline.markAsDone();
        deadline.markAsNotDone();

        assertFalse(deadline.isDone);
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", deadline.getStatusIcon());

        deadline.markAsDone();

        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void testTaskToStringData() {
        assertEquals("D| |task 1|2024-09-01 10:56", deadline.taskToStringData());

        deadline.markAsDone();

        assertEquals("D|X|task 1|2024-09-01 10:56", deadline.taskToStringData());
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] task 1 (by: Sep 01 2024 10:56)", deadline.toString());

        deadline.markAsDone();

        assertEquals("[D][X] task 1 (by: Sep 01 2024 10:56)", deadline.toString());
    }
}

