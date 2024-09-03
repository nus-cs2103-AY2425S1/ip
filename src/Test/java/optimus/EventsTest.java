package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    private Events event;

    @BeforeEach
    public void setUp() throws OptimusException {
        event = new Events("Test event", "1/09/2024 12:00", "1/09/2024 14:00");
    }

    @Test
    public void testParseStringValidFormat1() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat2() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat3() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat4() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void testParseStringNullInput() {
        assertThrows(OptimusException.class, () -> {
            event.parseStringEvent(null);
        });
    }

    @Test
    public void testParseStringEmptyInput() {
        assertThrows(OptimusException.class, () -> {
            event.parseStringEvent("  ");
        });
    }

    @Test
    public void testParseStringInvalidFormat() {
        String invalidDateTimeInput = "invalid date";
        assertThrows(OptimusException.class, () -> {
            event.parseStringEvent(invalidDateTimeInput);
        });
    }

    @Test
    public void testParseStringInvalidDateFormat() {
        String invalidDateTimeInput = "32/13/2024 12:00";
        assertThrows(OptimusException.class, () -> {
            event.parseStringEvent(invalidDateTimeInput);
        });
    }
}
