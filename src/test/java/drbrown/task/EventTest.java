package drbrown.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Event} class.
 * This class contains unit tests for various functionalities of the {@code Event} class,
 * including the creation of events, handling invalid date formats, handling invalid priorities,
 * and generating string representations for UI and file outputs.
 */
public class EventTest {

    private String description;
    private String validStartTime;
    private String validEndTime;
    private String invalidTime;
    private DateTimeFormatter fileDateTimeFormatter;
    private String invalidPriority;
    private Task.Priority validPriority;

    /**
     * Sets up test data before each test case.
     * Initializes common test data such as task description, valid and invalid start and end times,
     * a date-time formatter, and valid and invalid priorities.
     */
    @BeforeEach
    void setUp() {
        description = "Meeting";
        validStartTime = "03-09-2024 09:00";
        validEndTime = "03-09-2024 10:00";
        invalidTime = "03-09-24 09:00";
        fileDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        validPriority = Task.Priority.LOW;
        invalidPriority = "VERYHIGH";
    }

    /**
     * Tests the successful creation of an {@link Event} task with valid start and end times.
     * Ensures that the string representation of the created {@code Event} object matches the expected format.
     */
    @Test
    public void testSuccessfulCreationEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter), validPriority);
        assertEquals("[E][ ] Meeting | LOW (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }

    /**
     * Tests handling of an invalid date format when creating an {@link Event} task.
     * Verifies that a {@code DateTimeParseException} is thrown when an invalid date format is provided.
     */
    @Test
    public void testInvalidDateTimeEvent() {
        try {
            new Event(false, description,
                    LocalDateTime.parse(invalidTime, fileDateTimeFormatter),
                    LocalDateTime.parse(validEndTime, fileDateTimeFormatter), validPriority);
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests handling of an invalid priority when creating an {@link Event} task.
     * Verifies that an {@code IllegalArgumentException} is thrown when an invalid priority is provided.
     */
    @Test
    public void testInvalidPriorityEvent() {
        try {
            new Event(false, description,
                    LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                    LocalDateTime.parse(validEndTime, fileDateTimeFormatter), Task.Priority.valueOf(invalidPriority));
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests the file string representation of an {@link Event} task.
     * Ensures that the string representation returned by {@code toFileString()} matches the expected format.
     */
    @Test
    void testToFileStringEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter), validPriority);
        assertEquals("E | false | Meeting | LOW | 2024-09-03 0900 | 2024-09-03 1000", event.toFileString());
    }

    /**
     * Tests the UI string representation of an {@link Event} task.
     * Ensures that the string representation returned by {@code toUiString()} matches the expected format.
     */
    @Test
    void testToUiStringEvent() {
        Event event = new Event(false, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter), validPriority);
        assertEquals("The appropriate question is: 'When the hell are they?' "
                + "Your event is now set in time!\n", event.toUiString());
    }

    /**
     * Tests the successful creation of a marked (completed) {@link Event} task.
     * Ensures that the string representation of the created {@code Event} object when marked as done
     * matches the expected format.
     */
    @Test
    public void testSuccessfulCreationMarkDoneEvent() {
        Event event = new Event(true, description,
                LocalDateTime.parse(validStartTime, fileDateTimeFormatter),
                LocalDateTime.parse(validEndTime, fileDateTimeFormatter), validPriority);
        assertEquals("[E][X] Meeting | LOW (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }
}
