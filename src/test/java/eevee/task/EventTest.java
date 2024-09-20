package eevee.task;

import eevee.EeveeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() throws EeveeException {
        event = new Event("Meeting", "2023-09-20", "2023-09-21");
    }

    @Test
    public void testEventWithValidDates() {
        assertNotNull(event);
        assertEquals("Meeting", event.getDescription());
        assertEquals("Sep 20 2023", event.from);
        assertEquals("Sep 21 2023", event.to);
    }

    @Test
    public void testFormatDateWithValidDates() {
        String formattedDate = event.formatDate("2023-09-20");
        assertEquals("Sep 20 2023", formattedDate);
    }

    @Test
    public void testFormatDateWithInvalidDates() {
        String formattedDate = event.formatDate("invalid-date");
        assertEquals("invalid-date", formattedDate);
    }

    @Test
    public void testValidateDatesWithValidRange() {
        assertDoesNotThrow(() -> event.validateDates());
    }
}

