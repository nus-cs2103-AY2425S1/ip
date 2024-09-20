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
 * Tests for the {@link Deadline} class.
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
     * Tests the {@link Deadline} constructor with an invalid date format.
     * Verifies that a {@link DateTimeParseException} is thrown.
     */
    @Test
    public void testInvalidDateFormatInDeadlineConstructor() {
        String invalidDateString = "12/25/2023 6pm"; // Wrong format

        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime invalidDeadline = LocalDateTime.parse(invalidDateString,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            new Deadline("Submit assignment", invalidDeadline);
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }

    /**
     * Tests the {@link Deadline} constructor with various valid time formats.
     * Ensures that the deadline is correctly parsed and formatted.
     */
    @Test
    public void testValidDeadlineWithDifferentTimeFormats() {
        String[] validTimeFormats = {"25/12/2023 1800", "1/1/2024 0900", "31/12/2023 2359"};

        for (String validTimeFormat : validTimeFormats) {
            LocalDateTime deadline = LocalDateTime.parse(validTimeFormat,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Deadline deadlineTask = new Deadline("Submit assignment", deadline);

            assertEquals(deadline, deadlineTask.getDdl());
        }
    }

    /**
     * Tests the {@link Deadline#toString()} method.
     * Verifies that the deadline date is formatted in the correct output format.
     */
    @Test
    public void testDeadlineOutputFormat() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);

        String displayFormat = deadlineTask.toString();
        System.out.println(displayFormat);
        assertTrue(displayFormat.contains("Dec 25 2023, 6:00"));
    }

}

