package arts.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private Deadline deadline;
    private LocalDateTime dateTime;

    @BeforeEach
    public void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        dateTime = LocalDateTime.parse("2024-08-29 1200", formatter);
        deadline = new Deadline("Submit assignment", dateTime);
    }

    @Test
    public void testToString() {
        String expected = "[D][ ] Submit assignment (by: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, deadline.toString(), "The string representation of the deadline is incorrect.");
    }

    @Test
    public void testToFileFormat() {
        String expected = "D | 0 | Submit assignment | 2024-08-29 1200";
        assertEquals(expected, deadline.toFileFormat(), "The file format of the deadline is incorrect.");
    }

    @Test
    public void testMarkAsDone() {
        deadline.markAsDone();
        String expected = "[D][X] Submit assignment (by: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, deadline.toString(), "The deadline should be marked as done.");
    }
}