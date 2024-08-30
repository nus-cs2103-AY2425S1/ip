package wenjieBot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        deadline = new Deadline("Submit report", "31/12/2024 2359");
    }

    @Test
    public void testToPrettierString() {
        // Format: "D[description][ ]/by: d/M/yyyy HHmm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String expected = "D | 0 | Submit report/by: 31/12/2024 2359";
        assertEquals(expected, deadline.toPrettierString());
    }

    @Test
    public void testToString() {
        // Format: "[D][ ][ ](by: EEE, dd MMM yyyy HH:mm)"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm");
        String expectedDate = LocalDateTime.parse("31/12/2024 2359", DateTimeFormatter.ofPattern("d/M/yyyy HHmm")).format(formatter);
        String expected = "[D][ ] Submit report(by: Tue, 31 Dec 2024 23:59)";
        assertEquals(expected, deadline.toString());
    }
}
