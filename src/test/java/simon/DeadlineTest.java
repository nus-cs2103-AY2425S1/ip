package simon;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline task = new Deadline("Test Task", 1, deadline);
        String expected = "[D][ ] Test Task (by: Aug 26 2024 15:30)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToSaveFormat() {
        LocalDateTime deadline = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline task = new Deadline("Test Task", 1, deadline);
        String expected = "D | 0 | Test Task | 2024-08-26 1530";
        assertEquals(expected, task.toSaveFormat());
    }

    @Test
    public void testParseFromString() {
        Deadline task = Deadline.parseFromString("Test Task", 1, "2024-08-26 1530");
        LocalDateTime expectedDeadline = LocalDateTime.of(2024, 8, 26, 15, 30);
        assertEquals("Test Task", task.getName());
    }
}
