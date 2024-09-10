package echoa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testToText_CompletedTask() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish IP", true, dateTime);

        String result = deadline.toText();

        String expected = "D | 1 | Finish IP | 2024-08-30 14:30";
        assertEquals(expected, result, "The toText() method should return the correct string representation for a completed task.");
    }

    @Test
    public void testToText_UncompletedTask() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 16, 0);
        Deadline deadline = new Deadline("Submit IP", false, dateTime);

        String result = deadline.toText();

        String expected = "D | 0 | Submit IP | 2024-08-30 16:00";
        assertEquals(expected, result, "The toText() method should return the correct string representation for an uncompleted task.");
    }
}

