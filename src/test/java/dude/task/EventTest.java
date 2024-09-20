package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event;

    @BeforeEach
    public void setUp() {
        LocalDateTime fromDateTime = LocalDateTime.of(2024, 9, 1, 10, 56);
        LocalDateTime toDateTime = LocalDateTime.of(2024, 9, 11, 10, 56);
        event = new Event("task 1", fromDateTime, toDateTime);
    }

    @Test
    public void testConstructor() {
        LocalDateTime fromDateTime = LocalDateTime.of(1111, 11, 11, 11, 11);
        LocalDateTime toDateTime = LocalDateTime.of(2222, 2, 22, 22, 22);
        event = new Event("task 2", fromDateTime, toDateTime);

        assertEquals("task 2", event.description);
        assertEquals(LocalDateTime.of(1111, 11, 11, 11, 11), event.from);
        assertEquals(LocalDateTime.of(2222, 2, 22, 22, 22), event.to);
        assertFalse(event.isDone);
    }

    @Test
    public void testMarkAsDone() {
        event.markAsDone();

        assertTrue(event.isDone);
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void testMarkAsNotDone() {
        event.markAsDone();
        event.markAsNotDone();

        assertFalse(event.isDone);
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", event.getStatusIcon());

        event.markAsDone();

        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void testTaskToStringData() {
        assertEquals("E| |task 1|2024-09-01 10:56|2024-09-11 10:56", event.taskToStringData());

        event.markAsDone();

        assertEquals("E|X|task 1|2024-09-01 10:56|2024-09-11 10:56", event.taskToStringData());
    }

    @Test
    public void testToString() {
        assertEquals("[E][ ] task 1 (from: Sep 01 2024 10:56 to: Sep 11 2024 10:56)", event.toString());

        event.markAsDone();

        assertEquals("[E][X] task 1 (from: Sep 01 2024 10:56 to: Sep 11 2024 10:56)", event.toString());
    }

    @Test
    public void testEquals() {
        LocalDateTime sameFrom = LocalDateTime.of(2024, 9, 1, 10, 56);
        LocalDateTime sameTo = LocalDateTime.of(2024, 9, 11, 10, 56);

        LocalDateTime differentFrom = LocalDateTime.of(1111, 11, 11, 11, 11);
        LocalDateTime differentTo = LocalDateTime.of(2222, 2, 22, 22, 22);

        Event sameTask = new Event("task 1", sameFrom, sameTo);
        Event differentNameTask = new Event("task 2", sameFrom, sameTo);
        Event differentFromTask = new Event("task 1", differentFrom, sameTo);
        Event differentToTask = new Event("task 1", sameFrom, differentTo);

        assertEquals(event, sameTask);
        assertNotEquals(event, differentNameTask);
        assertNotEquals(event, differentFromTask);
        assertNotEquals(event, differentToTask);
    }
}

