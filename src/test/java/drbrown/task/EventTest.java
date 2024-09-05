package drbrown.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Event class.
 * Tests the creation, string representations, and error handling for Event tasks.
 */
public class EventTest {

    private String description;
    private String validStartTime;
    private String validEndTime;
    private String invalidTime;
    private DateTimeFormatter fileDateTimeFormatter;

    /**
     * Sets up test data before each test case.
     */
    @BeforeEach
    void setUp() {
        description = "Meeting";
        validStartTime = "03-09-2024 09:00";
        validEndTime = "03-09-2024 10:00";
        invalidTime = "03-09-24 09:00";
        fileDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    /**
     * Tests successful creation of an Event task with valid start and end times.
     */
    @Test
    public void testSuccessfulCreationEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter));
        assertEquals("[E][ ] Meeting (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }

    /**
     * Tests handling of invalid date format when creating an Event task.
     * Expects a DateTimeParseException to be thrown.
     */
    @Test
    public void testInvalidDateTimeEvent() {
        try {
            new Event(false, description,
                    LocalDateTime.parse(invalidTime, fileDateTimeFormatter),
                    LocalDateTime.parse(validEndTime, fileDateTimeFormatter));
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests the file string representation of an Event task.
     */
    @Test
    void testToFileStringEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter));
        assertEquals("E | false | Meeting | 2024-09-03 0900 | 2024-09-03 1000", event.toFileString());
    }

    /**
     * Tests the UI string representation of an Event task.
     */
    @Test
    void testToUiStringEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter));
        assertEquals("The appropriate question is: ‘When the hell are they?’ "
                + "Your event is now set in time!\n", event.toUiString());
    }

    /**
     * Tests successful creation of a marked (completed) Event task.
     */
    @Test
    public void testSuccessfulCreationMarkDoneEvent() {
        Event event = new Event(true, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter));
        assertEquals("[E][X] Meeting (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }
}
