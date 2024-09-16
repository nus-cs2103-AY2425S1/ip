package xizi.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import xizi.chatbot.task.Deadline;



/**
 * Unit tests for the {@link Deadline} class.
 * This class tests the functionality related to creating deadlines.
 */
public class DeadlineTest {

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
            LocalDateTime invalidDeadline = LocalDateTime.parse(invalidDateString,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            new Deadline("Submit assignment", invalidDeadline);
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }
}

