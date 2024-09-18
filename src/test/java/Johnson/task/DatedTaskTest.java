package Johnson.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatedTaskTest {

    @Test
    void testDatedTaskWithDateAndTime() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12", "12:00");
        assertEquals("Test Task", task.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), task.getDate());
        assertEquals(LocalTime.of(12, 0), task.getTime());
    }

    @Test
    void testDatedTaskWithDateOnly() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        assertEquals("Test Task", task.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), task.getDate());
        assertNull(task.getTime());
    }

    @Test
    void testSetDate() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        task.setDate("2025-01-01");
        assertEquals(LocalDate.of(2025, 1, 1), task.getDate());
    }

    @Test
    void testSetTime() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        task.setTime("14:30");
        assertEquals(LocalTime.of(14, 30), task.getTime());
    }

    @Test
    void testSetDateWithLocalDate() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        task.setDate(LocalDate.of(2025, 1, 1));
        assertEquals(LocalDate.of(2025, 1, 1), task.getDate());
    }

    @Test
    void testSetTimeWithLocalTime() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        task.setTime(LocalTime.of(14, 30));
        assertEquals(LocalTime.of(14, 30), task.getTime());
    }

    @Test
    void testAddTag() {
        DatedTask task = new DatedTask("Test Task", "2024-12-12");
        String tag = "newTag";
        task.addTag(tag);
        assertTrue(task.getTags().get(0).getTag().equals(tag));
    }
}