package bangmang.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2024, 9, 16, 23, 59);
        Deadline deadline = new Deadline("Test Deadline", by);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        String expected = "[D][ ] Test Deadline | " + by.format(formatter);
        assertEquals(expected, deadline.toString());
        deadline.markTask();
        assertTrue(deadline.getIsDone());
    }
}
