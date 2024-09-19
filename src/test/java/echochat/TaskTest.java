package echochat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private final Task task = new Task("Sample Task");

    @Test
    void testParseDateTimeWithDateTimeInput() {
        String dateTimeStr = "29/8/2024 1530";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 8, 29, 15, 30);
        LocalDateTime result = task.parseDateTime(dateTimeStr);

        assertNotNull(result, "The result should not be null for a valid date-time input.");
        assertEquals(expectedDateTime, result, "The parsed date-time should match the expected value.");
    }

    @Test
    void testParseDateTimeWithDateOnlyInput() {
        String dateStr = "29/8/2024";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 8, 29, 0, 0);
        LocalDateTime result = task.parseDateTime(dateStr);

        assertNotNull(result, "The result should not be null for a valid date-only input.");
        assertEquals(expectedDateTime, result, "The parsed date (defaulted to midnight) should match the expected value.");
    }

    @Test
    void testParseDateTimeWithInvalidInput() {
        String invalidStr = "invalid date string";
        LocalDateTime result = task.parseDateTime(invalidStr);

        assertNull(result, "The result should be null for an invalid date-time input.");
    }
}
