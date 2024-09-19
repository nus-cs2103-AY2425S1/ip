package pixy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pixy.tasks.Deadlines;

class DeadlinesTest {

    @Test
    void testValidDateFormat() {
        Deadlines deadline = new Deadlines("Complete report", "31/8/2024 0900");
        assertEquals("Aug 31 2024, 9:00 am", deadline.getDueDateTime());
    }

    @Test
    void testInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Deadlines("Complete report", "31-08-2024 0900");
        });
        assertTrue(exception.getMessage().contains("Invalid date format: 31-08-2024 0900" +
                ". Please use 'd/M/yyyy HHmm' or 'MMM d yyyy, h:mm a'."));
    }

    @Test
    void testDifferentDateFormat() {
        Deadlines deadline = new Deadlines("Submit report", "Aug 31 2024, 9:00 am");
        assertEquals("Aug 31 2024, 9:00 am", deadline.getDueDateTime());
    }

    @Test
    void testToString() {
        Deadlines deadline = new Deadlines("Submit report", "31/8/2024 0900");
        assertEquals("[D][ ] Submit report (by: Aug 31 2024, 9:00 am)", deadline.toString());
    }
}
