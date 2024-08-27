package lama.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    public void ConstructorTest() {
        String description = "Return Book";
        LocalDateTime from = LocalDateTime.of(2025, 12, 12, 20, 10);
        LocalDateTime to = LocalDateTime.of(2026, 12, 12, 20, 10);
        Task event = new Event(description, from, to);

        String output = "[E][ ] Return Book (from: Dec 12 2025 20:10 to: Dec 12 2026 20:10)";

        assertEquals(output, event.toString());
    }

    @Test
    public void toFileTest() {
        String description = "Return Book";
        LocalDateTime from = LocalDateTime.of(2025, 12, 12, 20, 10);
        LocalDateTime to = LocalDateTime.of(2026, 12, 12, 20, 10);
        Task event = new Event(description, from ,to);

        String output = "E | 0 | Return Book | 2025-12-12 2010 | 2026-12-12 2010";

        assertEquals(output, event.toFile());
    }

}
