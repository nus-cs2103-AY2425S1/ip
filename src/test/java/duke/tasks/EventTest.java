package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



public class EventTest {
    @Test
    public void testUnmarked() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Event task = new Event("event", start, end);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String expected = String.format("[E][ ] event (from: %s to: %s)", start.format(f), end.format(f));
        assertEquals(task.toString(), expected);
    }

    @Test
    public void testMarked() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Event task = new Event("event", start, end);
        task.mark(true);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String expected = String.format("[E][X] event (from: %s to: %s)", start.format(f), end.format(f));
        assertEquals(task.toString(), expected);
    }
}
