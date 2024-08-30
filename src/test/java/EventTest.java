package main.java.angel;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testCreateEventValid() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Project meeting", from, to);

        // Act & Assert
        assertNotNull(event);
        assertEquals("[E][ ] Project meeting (from: Oct 15 2024, 1400, to: Oct 15 2024, 1600)", event.toString());
    }

    @Test
    void testToSaveFormat() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Project meeting", from, to);

        // Act
        String encoded = event.toSaveFormat();

        // Assert
        assertEquals("E | 0 | Project meeting | 2024/10/15 1400 | 2024/10/15 1600", encoded);
    }

    @Test
    void testCreateEventInvalid() {
        // Test case for invalid date or time input (null values).
        assertThrows(NullPointerException.class, () -> new Event("Invalid event", null, null));
    }

    @Test
    void testSetStatus() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Team meeting", from, to);

        // Act
        event.markAsDone();

        // Assert
        assertEquals("[E][X] Team meeting (from: Oct 15 2024, 1400, to: Oct 15 2024, 1600)", event.toString());
    }
}
