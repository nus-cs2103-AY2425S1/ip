package bitbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class EventsTest {
    @Test
    public void testFinalString_withLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 16, 0);
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 8, 30, 18, 0);
        Events event = new Events("Conference", dateTime, dateTime1);
        String expected = "[E][ ] Conference (from: Aug 30 2024 16:00 to: Aug 30 2024 18:00)";
        assertEquals(expected, event.finalString());
    }
    @Test
    public void testFinalString_withLocalDate() {
        LocalDate date = LocalDate.of(2024, 8, 30);
        LocalDate date1 = LocalDate.of(2024, 8, 31);
        Events event = new Events("Conference", date, date1);
        String expected = "[E][ ] Conference (from: Aug 30 2024 to: Aug 31 2024)";
        assertEquals(expected, event.finalString());
    }

    @Test
    public void testFinalString_withLocalTime() {
        LocalTime time = LocalTime.of(16, 0);
        LocalTime time1 = LocalTime.of(18, 0);
        Events event = new Events("Conference", time, time1);
        String expected = "[E][ ] Conference (from: 16:00 to: 18:00)";
        assertEquals(expected, event.finalString());
    }

    @Test
    public void toFileFormat_withLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 16, 0);
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 8, 30, 18, 0);
        Events event = new Events("Conference", dateTime, dateTime1);
        String expected = "E| |Conference|Aug 30 2024 16:00|Aug 30 2024 18:00";
        assertEquals(expected, event.toFileFormat());
    }
    @Test
    public void toFileFormat_withLocalDate() {
        LocalDate date = LocalDate.of(2024, 8, 30);
        LocalDate date1 = LocalDate.of(2024, 8, 30);
        Events event = new Events("Conference", date, date1);
        String expected = "E| |Conference|Aug 30 2024|Aug 30 2024";
        assertEquals(expected, event.toFileFormat());
    }
    @Test
    public void toFileFormat_withLocalTime() {
        LocalTime time = LocalTime.of(16, 0);
        LocalTime time1 = LocalTime.of(18, 0);
        Events event = new Events("Conference", time, time1);
        String expected = "E| |Conference|16:00|18:00";
        assertEquals(expected, event.toFileFormat());
    }

    @Test
    public void event_invalidTimeFormat_throwsDateTimeParseException() {

        try {
            String invalidTime = "25:00";
            String description = "Conference";
            String input = description + " /from " + invalidTime + " /to " + invalidTime;
            Task deadline = new Events(description, LocalTime.parse(invalidTime), LocalTime.parse(invalidTime));
            fail();
        } catch (DateTimeParseException e) {
            System.out.println("Error");
        }
    }


}
