package lama.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class for Event class.
 * Contains unit test case for event class.
 */
public class EventTest {

    /**
     * Test the constructor of event class.
     * Verifies that an Event object is correctly initialized and
     * its toString method returns the expected format.
     */
    @Test
    public void constructorTest() {
        String description = "Return Book";
        LocalDateTime from = LocalDateTime.of(2025, 12, 12, 20, 10);
        LocalDateTime to = LocalDateTime.of(2026, 12, 12, 20, 10);
        Task event = new Event(description, from, to);

        String output = "[E][ ] Return Book (from: Dec 12 2025 20:10 to: Dec 12 2026 20:10)";

        assertEquals(output, event.toString());
    }

    /**
     * Test the toFile method.
     * Verifies that the toFile method returns the correct string representation
     * of the Event object for file storage.
     */
    @Test
    public void toFileTest() {
        String description = "Return Book";
        LocalDateTime from = LocalDateTime.of(2025, 12, 12, 20, 10);
        LocalDateTime to = LocalDateTime.of(2026, 12, 12, 20, 10);
        Task event = new Event(description, from, to);

        String output = "E | 0 | Return Book | 2025-12-12 2010 | 2026-12-12 2010";

        assertEquals(output, event.toFileFormat());
    }

}
