package drbrown.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the Deadline class.
 * Tests the creation, string representations, and error handling for Deadline tasks.
 */
public class DeadlineTest {
    private String description;
    private String validTime;
    private String invalidTime;
    private DateTimeFormatter formatter;

    /**
     * Sets up test data before each test case.
     */
    @BeforeEach
    void setUp() {
        description = "Assignment";
        validTime = "03-09-2024 10:10";
        invalidTime = "03-09-24 10:10";
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    /**
     * Tests successful creation of a Deadline task with valid date and time.
     */
    @Test
    public void testSuccessfulCreationDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("[D][ ] Assignment (by: Sep 03 2024 10:10)", deadline.toString());
    }

    /**
     * Tests handling of invalid date format when creating a Deadline task.
     * Expects a DateTimeParseException to be thrown.
     */
    @Test
    public void testInvalidDateTimeDeadline() {
        try {
            Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.invalidTime, formatter));
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
        }
    }

    /**
     * Tests the file string representation of a Deadline task.
     */
    @Test
    void testToFileStringDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("D | false | Assignment | 2024-09-03 1010", deadline.toFileString());
    }

    /**
     * Tests the UI string representation of a Deadline task.
     */
    @Test
    void testToUIStringDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet this deadline... he'd melt your brain! So, better get moving!\n", deadline.toUIString());
    }

    /**
     * Tests successful creation of a marked (completed) Deadline task.
     */
    @Test
    public void testSuccessfulCreationMarkDoneDeadline() {
        Deadline deadline = new Deadline(true, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("[D][X] Assignment (by: Sep 03 2024 10:10)", deadline.toString());
    }
}
