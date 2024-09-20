package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeadlineTest extends Task {
    public LocalDateTime by;

    public DeadlineTest(String des, String by) {
        super(des);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Hey man, you have to use the yyyy-mm-dd HHmm format instead");
            this.by = null;
        }
    }

    @Test
    public void testValidDateParsing() {
        DeadlineTest deadline = new DeadlineTest("Submit assignment", "2024-12-01 1800");
        LocalDateTime expectedDate = LocalDateTime.of(2024, 12, 1, 18, 0);

        assertEquals(expectedDate, deadline.by, "Check if date is parsed correctly");
    }

    @Test
    public void testInvalidDateParsing() {
        DeadlineTest deadline = new DeadlineTest("Submit assignment", "1234");

        assertNull(deadline.by, "The date should be null when parsing fails.");
    }

    @Test
    public void testToStringWithValidDate() {
        DeadlineTest deadline = new DeadlineTest("Submit assignment", "2024-12-01 1800");
        String expectedOutput = "[D][ ] Submit assignment (by: Dec 01 2024 18:00)";

        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testToStringWithInvalidDate() {
        DeadlineTest deadline = new DeadlineTest("Submit assignment", "invalid-date");
        String expectedOutput = "[D][ ] Submit assignment (by: I did not get the date, use yyyy-mm-dd HHmm.)";

        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testToStringWithNullDate() {
        DeadlineTest deadline = new DeadlineTest("Submit assignment", null);
        String expectedOutput = "[D][ ] Submit assignment (by: I did not get the date, use yyyy-mm-dd HHmm.)";

        assertEquals(expectedOutput, deadline.toString());
    }
}