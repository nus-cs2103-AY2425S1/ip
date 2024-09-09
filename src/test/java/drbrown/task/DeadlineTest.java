package drbrown.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Deadline} class.
 * This class contains unit tests for various functionalities of the {@code Deadline} class,
 * including the creation of deadlines, handling invalid date formats, handling invalid priorities,
 * and generating string representations for UI and file outputs.
 */
public class DeadlineTest {

    private String description;
    private String validTime;
    private String invalidTime;
    private DateTimeFormatter fileDateTimeFormatter;
    private String invalidPriority;
    private Task.Priority validPriority;

    /**
     * Sets up test data before each test case.
     * Initializes common test data such as task description, valid and invalid date formats,
     * a date-time formatter, and valid and invalid priorities.
     */
    @BeforeEach
    void setUp() {
        description = "Assignment";
        validTime = "03-09-2024 10:10";
        invalidTime = "03-09-24 10:10";
        fileDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        validPriority = Task.Priority.LOW;
        invalidPriority = "VERYHIGH";
    }

    /**
     * Tests the successful creation of a {@link Deadline} task with valid date and time.
     * Ensures that the string representation of the created {@code Deadline} object matches the expected format.
     */
    @Test
    public void testSuccessfulCreationDeadline() {
        Deadline deadline = new Deadline(false, description,
                LocalDateTime.parse(validTime, fileDateTimeFormatter), validPriority);
        assertEquals("[D][ ] Assignment | LOW (by: Sep 03 2024 10:10)", deadline.toString());
    }

    /**
     * Tests handling of an invalid date format when creating a {@link Deadline} task.
     * Verifies that a {@code DateTimeParseException} is thrown when an invalid date format is provided.
     */
    @Test
    public void testInvalidDateTimeDeadline() {
        try {
            new Deadline(false, description,
                    LocalDateTime.parse(invalidTime, fileDateTimeFormatter), validPriority);
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests handling of an invalid priority when creating a {@link Deadline} task.
     * Verifies that an {@code IllegalArgumentException} is thrown when an invalid priority is provided.
     */
    @Test
    public void testInvalidPriorityDeadline() {
        try {
            new Deadline(false, description,
                    LocalDateTime.parse(validTime, fileDateTimeFormatter),
                    Task.Priority.valueOf(invalidPriority));
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests the file string representation of a {@link Deadline} task.
     * Ensures that the string representation returned by {@code toFileString()} matches the expected format.
     */
    @Test
    void testToFileStringDeadline() {
        Deadline deadline = new Deadline(false, description,
                LocalDateTime.parse(validTime, fileDateTimeFormatter), validPriority);
        assertEquals("D | false | Assignment | LOW | 2024-09-03 1010", deadline.toFileString());
    }

    /**
     * Tests the UI string representation of a {@link Deadline} task.
     * Ensures that the string representation returned by {@code toUiString()} matches the expected format.
     */
    @Test
    void testToUiStringDeadline() {
        Deadline deadline = new Deadline(false, description,
                LocalDateTime.parse(validTime, fileDateTimeFormatter), validPriority);
        assertEquals("Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet "
                        + "this deadline... he'd melt your brain! So, better get moving!\n",
                deadline.toUiString());
    }

    /**
     * Tests the successful creation of a marked (completed) {@link Deadline} task.
     * Ensures that the string representation of the created {@code Deadline} object when marked as done
     * matches the expected format.
     */
    @Test
    public void testSuccessfulCreationMarkDoneDeadline() {
        Deadline deadline = new Deadline(true, description,
                LocalDateTime.parse(validTime, fileDateTimeFormatter), validPriority);
        assertEquals("[D][X] Assignment | LOW (by: Sep 03 2024 10:10)", deadline.toString());
    }
}
