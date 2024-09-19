package elon;

import elon.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("Buy book", false, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 3));

        String result = event.toString();

        assertEquals("[E][ ] Buy book (from: Jan 1 2024 to: Jan 3 2024)", result);
    }

    @Test
    public void testToFileString() {
        Event event = new Event("Return book", true, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 3));

        String result = event.toFileString();

        assertEquals("E | 1 | Return book | 2024-01-01 | 2024-01-03", result);
    }
}
