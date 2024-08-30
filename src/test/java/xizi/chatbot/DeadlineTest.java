package xizi.chatbot;

import org.junit.jupiter.api.Test;
import xizi.chatbot.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Deadline} class.
 * This class tests the functionality related to creating deadlines, formatting them for output, and handling invalid dates.
 */
public class DeadlineTest {

    /**
     * Tests the {@link Deadline#toString()} method.
     * Verifies that the string representation of a deadline task is formatted correctly.
     */
    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);

        String result = deadlineTask.toString();
        assertEquals("[D][ ] Submit assignment (by: Dec 25 2023, 6:00pm)", result);
    }


    /**
     * Tests the {@link Deadline#toFileFormat()} method.
     * Verifies that the file format of a deadline task is generated correctly.
     */
    @Test
    public void testToFileFormat() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);

        String result = deadlineTask.toFileFormat();
        assertEquals("D | 0 | Submit assignment | Dec 25 2023, 6:00pm", result);
    }

    /**
     * Tests the {@link Deadline#getDdl()} method.
     * Verifies that the deadline date and time are returned correctly.
     */
    @Test
    public void testGetDdl() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);
        LocalDateTime result = deadlineTask.getDdl();
        assertEquals(deadline, result);
    }

    /**
     * Tests the handling of an invalid date string using {@link LocalDateTime#parse(CharSequence, DateTimeFormatter)}.
     * Verifies that a {@link DateTimeParseException} is thrown when attempting to parse an invalid date string.
     */
    @Test
    public void testInvalidDate() {
        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse("32/12/2023 1800", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }

    /**
     * Tests the {@link Deadline} constructor with an invalid date string.
     * Verifies that a {@link DateTimeParseException} is thrown when trying to create a deadline with an invalid date.
     */
    @Test
    public void testInvalidDateInDeadlineConstructor() {
        // Arrange
        String invalidDateString = "32/12/2023 1800";

        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime invalidDeadline = LocalDateTime.parse(invalidDateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            new Deadline("Submit assignment", invalidDeadline);
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }
}

