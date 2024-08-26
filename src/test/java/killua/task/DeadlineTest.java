package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testFormatWithDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);

        String expectedFormat = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        assertEquals(expectedFormat, deadline.format());
    }

    @Test
    public void testFormatWithDate() {
        LocalDate date = LocalDate.of(2024, 8, 26);
        Deadline deadline = new Deadline("Submit report", date);

        String expectedFormat = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assertEquals(expectedFormat, deadline.format());
    }

    @Test
    public void testGetDateWithDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);
        assertEquals(dateTime.toLocalDate(), deadline.getDate());
    }

    @Test
    public void testGetDateWithDate() {
        LocalDate date = LocalDate.of(2024, 8, 26);
        Deadline deadline = new Deadline("Submit report", date);
        assertEquals(date, deadline.getDate());
    }

    @Test
    public void testGetDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);
        assertEquals(dateTime, deadline.getDateTime());
    }

    @Test
    public void testDeadlineToSaveWithDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);

        String expectedSaveFormat = "D | 0 | Submit report | Aug 26 2024 15:30";
        assertEquals(expectedSaveFormat, deadline.toSave());
    }

    @Test
    public void testDeadlineToSaveWithDate() {
        LocalDate date = LocalDate.of(2024, 8, 26);
        Deadline deadline = new Deadline("Submit report", date);

        String expectedSaveFormat = "D | 0 | Submit report | Aug 26 2024";
        assertEquals(expectedSaveFormat, deadline.toSave());
    }

    @Test
    public void testDeadlineToStringWithDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 15, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);

        String expectedStringFormat = "[D][ ] Submit report (by: Aug 26 2024 15:30)";
        assertEquals(expectedStringFormat, deadline.toString());
    }

    @Test
    public void testDeadlineToStringWithDate() {
        LocalDate date = LocalDate.of(2024, 8, 26);
        Deadline deadline = new Deadline("Submit report", date);

        String expectedStringFormat = "[D][ ] Submit report (by: Aug 26 2024)";
        assertEquals(expectedStringFormat, deadline.toString());
    }
}

