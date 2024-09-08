package main.java.angel;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the Event class.
 */
class EventTest {

    /**
     * Tests the creation of an Event instance with valid description, start, and end date/time.
     */
    @Test
    void testCreateEventValid() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Project meeting", from, to);

        // Act & Assert
        assertNotNull(event, "The event should not be null");
        assertEquals("[E][ ] Project meeting (from: Oct 15 2024, 1400, to: Oct 15 2024, 1600)", event.toString(),
                "The string representation of the event is incorrect");
    }

    /**
     * Tests the save format of an Event instance.
     */
    @Test
    void testToSaveFormat() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Project meeting", from, to);

        // Act
        String encoded = event.toSaveFormat();

        // Assert
        assertEquals("E | 0 | Project meeting | 2024/10/15 1400 | 2024/10/15 1600", encoded,
                "The save format of the event is incorrect");
    }

    /**
     * Tests creating an Event with invalid date or time inputs (null values).
     */
    @Test
    void testCreateEventInvalid() {
        // Test case for invalid date or time input (null values).
        assertThrows(NullPointerException.class, () -> new Event("Invalid event", null, null),
                "Creating an event with null date/time should throw a NullPointerException");
    }

    /**
     * Tests marking an Event as done.
     */
    @Test
    void testSetStatus() {
        // Arrange
        LocalDateTime from = LocalDateTime.of(2024, 10, 15, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 10, 15, 16, 0);
        Event event = new Event("Team meeting", from, to);

        // Act
        event.markAsDone();

        // Assert
        assertEquals("[E][X] Team meeting (from: Oct 15 2024, 1400, to: Oct 15 2024, 1600)", event.toString(),
                "The string representation of the event after marking as done is incorrect");
    }
}
