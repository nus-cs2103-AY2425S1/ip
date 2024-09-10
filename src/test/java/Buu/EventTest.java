package Buu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



/**
 * JUnit test class for the Event class in the GPT application.
 */
public class EventTest {

    @Test
    public void testToFileFormat_withValidEvent() {
        // Arrange
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        // Act
        String actual = event.toFileFormat();

        // Assert
        String expected = "E | 0 | Team meeting | 2023-08-26 1000 | 2023-08-26 1200 | 1";
        assertEquals(expected, actual);
    }

    @Test
    public void testToString_withValidEvent() {
        // Arrange
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        // Act
        String actual = event.toString();

        // Assert
        String expected = "[E][ ] Team meeting (from: Aug 26 2023, 10:00am "
                + "to: Aug 26 2023, 12:00pm) (Priority: Low Priority)";
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkAsDone() {
        // Arrange
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        // Act
        event.markAsDone();
        String actual = event.toString();

        // Assert
        String expected = "[E][X] Team meeting (from: Aug 26 2023, 10:00am "
                + "to: Aug 26 2023, 12:00pm) (Priority: Low Priority)";
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPriority() {
        // Arrange
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        // Act
        event.setPriority(3);
        String actual = event.toString();

        // Assert
        String expected = "[E][ ] Team meeting (from: Aug 26 2023, 10:00am "
                + "to: Aug 26 2023, 12:00pm) (Priority: High Priority)";
        assertEquals(expected, actual);
    }
}
