package FRIDAY;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testConstructor() {
        // Test constructor and basic getters
        Deadline deadline = new Deadline("Submit report", "2024-12-01", 1);
        assertEquals("Submit report", deadline.getTaskDescription());
        assertEquals(LocalDate.of(2024, 12, 1), deadline.getTaskDeadline());
        assertEquals(true, deadline.getType());
    }

    @Test
    void testStorageDisplay() {
        // Test storage display format
        Deadline deadline = new Deadline("Submit report", "2024-12-01", 1);
        String expectedStorageDisplay = "D | 1 | Submit report | 2024-12-01";
        assertEquals(expectedStorageDisplay, deadline.storageDisplay());
    }

    @Test
    void testToString() {
        // Test string output format
        Deadline deadline = new Deadline("Submit report", "2024-12-01", 1);
        String expectedToString = "[D][X] Submit report(DECEMBER 1 2024)";
        assertEquals(expectedToString, deadline.toString());
    }
}
