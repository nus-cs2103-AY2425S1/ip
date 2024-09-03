package Optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlinesTest {

    private Deadlines deadline;

    @BeforeEach
    public void setUp() throws OptimusException {
        deadline = new Deadlines("Test deadline", "1/09/2024 12:00");
    }

    @Test
    public void testParseStringValidFormat1() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat2() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat3() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat4() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringNullInput() {
        assertThrows(OptimusException.class, () -> {
            deadline.parseStringDeadline(null);
        });
    }

    @Test
    public void testParseStringEmptyInput() {
        assertThrows(OptimusException.class, () -> {
            deadline.parseStringDeadline("  ");
        });
    }

    @Test
    public void testParseStringInvalidFormat() {
        String invalidDateTimeInput = "invalid date";
        assertThrows(OptimusException.class, () -> {
            deadline.parseStringDeadline(invalidDateTimeInput);
        });
    }

    @Test
    public void testParseStringInvalidDateFormat() {
        String invalidDateTimeInput = "32/13/2024 12:00";
        assertThrows(OptimusException.class, () -> {
            deadline.parseStringDeadline(invalidDateTimeInput);
        });
    }
}
