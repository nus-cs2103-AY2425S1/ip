package Johnson.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class DeadlineTest {

    @Test
    void testDeadlineWithDateAndTime() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12", "12:00", new String[] {"tag1", "tag2"});
        assertEquals("Test Deadline", deadline.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), deadline.getDate());
        assertEquals(LocalTime.of(12, 0), deadline.getTime());
        String tag1 = "tag1";
        String tag2 = "tag2";
        assertEquals(tag1, deadline.getTags().toArray()[0].toString());
        assertEquals(tag2, deadline.getTags().toArray()[1].toString());
    }

    @Test
    void testDeadlineWithDateOnly() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12", new String[] {"tag1", "tag2"});
        assertEquals("Test Deadline", deadline.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), deadline.getDate());
        assertNull(deadline.getTime());
        String tag1 = "tag1";
        String tag2 = "tag2";
        assertEquals(tag1, deadline.getTags().toArray()[0].toString());
        assertEquals(tag2, deadline.getTags().toArray()[1].toString());
    }

    @Test
    void testSetDate() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12");
        deadline.setDate("2025-01-01");
        assertEquals(LocalDate.of(2025, 1, 1), deadline.getDate());
    }

    @Test
    void testSetTime() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12");
        deadline.setTime("14:30");
        assertEquals(LocalTime.of(14, 30), deadline.getTime());
    }

    @Test
    void testSetDateWithLocalDate() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12");
        deadline.setDate(LocalDate.of(2025, 1, 1));
        assertEquals(LocalDate.of(2025, 1, 1), deadline.getDate());
    }

    @Test
    void testSetTimeWithLocalTime() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12");
        deadline.setTime(LocalTime.of(14, 30));
        assertEquals(LocalTime.of(14, 30), deadline.getTime());
    }

    @Test
    void testAddTag() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12");
        deadline.addTag("newTag");
        String newTag = "newTag";
        assertEquals(newTag, deadline.getTags().toArray()[0].toString());
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Test Deadline", "2024-12-12", "12:00", new String[] {});
        assertEquals("[D][ ] Test Deadline [] (by: 2024-12-12 12:00)", deadline.toString());
    }
}