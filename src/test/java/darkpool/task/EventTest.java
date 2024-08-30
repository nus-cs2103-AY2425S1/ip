package darkpool.task;

import darkpool.util.DarkpoolException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private DateTimeFormatter formatter;
    private String validFromTime;
    private String validToTime;
    private String invalidTime;
    private String description;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        validFromTime = "30-08-2024 10:00";
        validToTime = "30-08-2024 12:00";
        invalidTime = "30-08-2024 10:00:00"; // Invalid format
        description = "Project meeting";
    }

    @Test
    void testEventCreationSuccess() throws DarkpoolException {
        Event event = new Event(description, validFromTime, validToTime, false);
        assertNotNull(event);
        assertEquals(LocalDateTime.parse(validFromTime, formatter), event.fromTime);
        assertEquals(LocalDateTime.parse(validToTime, formatter), event.toTime);
    }

    @Test
    void testEventCreationInvalidTimeFormat() {
        DarkpoolException exception = assertThrows(DarkpoolException.class, () -> {
            new Event(description, invalidTime, validToTime, false);
        });
        assertTrue(exception.getMessage().contains("could not be parsed"));
    }

    @Test
    void testToString() throws DarkpoolException {
        Event event = new Event(description, validFromTime, validToTime, false);
        String expected = "[E][ ] Project meeting (from:30-08-2024 10:00 to:30-08-2024 12:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    void testToFileString() throws DarkpoolException {
        Event event = new Event(description, validFromTime, validToTime, false);
        String expected = "E | 0 | Project meeting | 30-08-2024 10:00 | 30-08-2024 12:00\n";
        assertEquals(expected, event.toFileString());
    }

    @Test
    void testEventCreationWithIsDoneTrue() throws DarkpoolException {
        Event event = new Event(description, validFromTime, validToTime, true);
        String expectedString = "[E][X] Project meeting (from:30-08-2024 10:00 to:30-08-2024 12:00)";
        assertEquals(expectedString, event.toString());
    }
}
