package bitbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class DeadlineTest {
    @Test
    public void testFinalString_withLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 16, 0);
        Deadline deadline = new Deadline("Submit report", dateTime);
        String expected = "[D][ ] Submit report (by: Aug 30 2024 16:00)";
        assertEquals(expected, deadline.finalString());
    }

    @Test
    public void testFinalString_withLocalDate() {
        LocalDate date = LocalDate.of(2024, 8, 30);
        Deadline deadline = new Deadline("Submit report", date);
        String expected = "[D][ ] Submit report (by: Aug 30 2024)";
        assertEquals(expected, deadline.finalString());
    }

    @Test
    public void testFinalString_withLocalTime() {
        LocalTime time = LocalTime.of(16, 0);
        Deadline deadline = new Deadline("Submit report", time);
        String expected = "[D][ ] Submit report (by: 16:00)";
        assertEquals(expected, deadline.finalString());
    }

    @Test
    public void toFileFormat_withLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 16, 0);
        Deadline deadline = new Deadline("Submit report", dateTime);
        String expected = "D| |Submit report|Aug 30 2024 16:00|NIL";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_withLocalDate() {
        LocalDate date = LocalDate.of(2024, 8, 30);
        Deadline deadline = new Deadline("Submit report", date);
        deadline.markAsDone();
        String expected = "D|X|Submit report|Aug 30 2024|NIL";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_withLocalTime() {
        LocalTime time = LocalTime.of(16, 0);
        Deadline deadline = new Deadline("Submit report", time);
        String expected = "D| |Submit report|16:00|NIL";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void deadline_invalidTimeFormat_throwsDateTimeParseException() {
        try {
            String invalidTime = "25:00";
            String description = "Submit report";
            String input = description + " /by " + invalidTime;
            Task deadline = new Deadline(description, LocalTime.parse(invalidTime));
            fail();
        } catch (DateTimeParseException e) {
            System.out.println("Error");
        }
    }
}