package xizi.chatbot;

import org.junit.jupiter.api.Test;
import xizi.chatbot.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);

        String result = deadlineTask.toString();
        assertEquals("[D][ ] Submit assignment (by: Dec 25 2023, 6:00pm)", result);
    }

    @Test
    public void testToFileFormat() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);

        String result = deadlineTask.toFileFormat();
        assertEquals("D | 0 | Submit assignment | Dec 25 2023, 6:00pm", result);
    }

    @Test
    public void testGetDdl() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 25, 18, 0);
        Deadline deadlineTask = new Deadline("Submit assignment", deadline);
        LocalDateTime result = deadlineTask.getDdl();
        assertEquals(deadline, result);
    }

    @Test
    public void testInvalidDate() {
        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse("32/12/2023 1800", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }

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

