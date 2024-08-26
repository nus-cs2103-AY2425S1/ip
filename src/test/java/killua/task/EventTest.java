package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testFormatWithDateTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 26, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 26, 12, 0);
        Event event = new Event("Meeting", startDateTime, endDateTime);

        String[] expectedFormat = {
                startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
        };

        assertEquals(expectedFormat[0], event.format()[0]);
        assertEquals(expectedFormat[1], event.format()[1]);
    }

    @Test
    public void testFormatWithDate() {
        LocalDate startDate = LocalDate.of(2024, 8, 26);
        LocalDate endDate = LocalDate.of(2024, 8, 27);
        Event event = new Event("Meeting", startDate, endDate);

        String[] expectedFormat = {
                startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        };

        assertEquals(expectedFormat[0], event.format()[0]);
        assertEquals(expectedFormat[1], event.format()[1]);
    }

    @Test
    public void testGetStartDateWithDateTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 26, 10, 0);
        Event event = new Event("Meeting", startDateTime, null);
        assertEquals(startDateTime.toLocalDate(), event.getStartDate());
    }

    @Test
    public void testGetStartDateWithDate() {
        LocalDate startDate = LocalDate.of(2024, 8, 26);
        Event event = new Event("Meeting", startDate, null);
        assertEquals(startDate, event.getStartDate());
    }

    @Test
    public void testGetEndDateWithDateTime() {
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 26, 12, 0);
        Event event = new Event("Meeting", null, endDateTime);
        assertEquals(endDateTime.toLocalDate(), event.getEndDate());
    }

    @Test
    public void testGetEndDateWithDate() {
        LocalDate endDate = LocalDate.of(2024, 8, 27);
        Event event = new Event("Meeting", null, endDate);
        assertEquals(endDate, event.getEndDate());
    }

    @Test
    public void testEventToSaveWithDateTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 26, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 26, 12, 0);
        Event event = new Event("Meeting", startDateTime, endDateTime);

        String expectedSaveFormat = "E | 0 | Meeting | Aug 26 2024 10:00 | Aug 26 2024 12:00";
        assertEquals(expectedSaveFormat, event.toSave());
    }

    @Test
    public void testEventToSaveWithDate() {
        LocalDate startDate = LocalDate.of(2024, 8, 26);
        LocalDate endDate = LocalDate.of(2024, 8, 27);
        Event event = new Event("Meeting", startDate, endDate);

        String expectedSaveFormat = "E | 0 | Meeting | Aug 26 2024 | Aug 27 2024";
        assertEquals(expectedSaveFormat, event.toSave());
    }

    @Test
    public void testEventToStringWithDateTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 26, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 26, 12, 0);
        Event event = new Event("Meeting", startDateTime, endDateTime);

        String expectedStringFormat = "[E][ ] Meeting (from: Aug 26 2024 10:00 to: Aug 26 2024 12:00)";
        assertEquals(expectedStringFormat, event.toString());
    }

    @Test
    public void testEventToStringWithDate() {
        LocalDate startDate = LocalDate.of(2024, 8, 26);
        LocalDate endDate = LocalDate.of(2024, 8, 27);
        Event event = new Event("Meeting", startDate, endDate);

        String expectedStringFormat = "[E][ ] Meeting (from: Aug 26 2024 to: Aug 27 2024)";
        assertEquals(expectedStringFormat, event.toString());
    }

    @Test
    public void testGetStartDateTime() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 26, 10, 0);
        Event event = new Event("Meeting", startDateTime, startDateTime.plusHours(2));
        assertEquals(startDateTime, event.getStartDateTime());
    }

    @Test
    public void testGetEndDateTime() {
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 26, 12, 0);
        Event event = new Event("Meeting", endDateTime.minusHours(2), endDateTime);
        assertEquals(endDateTime, event.getEndDateTime());
    }
}
