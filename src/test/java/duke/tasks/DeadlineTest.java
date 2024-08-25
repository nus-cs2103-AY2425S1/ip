package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testUnmarked() {
        LocalDateTime now = LocalDateTime.now();
        Deadline task = new Deadline("deadline", now);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String expected = String.format("[D][ ] deadline (by: %s)", now.format(f));
        assertEquals(task.toString(), expected);
    }

    @Test
    public void testMarked() {
        LocalDateTime now = LocalDateTime.now();
        Deadline task = new Deadline("deadline", now);
        task.mark(true);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String expected = String.format("[D][X] deadline (by: %s)", now.format(f));
        assertEquals(task.toString(), expected);
    }
}
