package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//Used GPT to help with refining parts of the unit tests helping with more coverage
class EventsTest {

    private Event event;

    @BeforeEach
    public void setUp() throws OptimusException {
        event = new Event("Test event", "1/09/2024 12:00", "1/09/2024 14:00");
    }

    @Test
    public void parseStringEvent_validFormatSlashDmy_expectSuccess() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_validFormatHyphenYmd_expectSuccess() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_validFormatHyphenDmy_expectSuccess() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_validFormatSlashYmd_expectSuccess() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_emptyInput_exceptionThrown() {
        String dateTimeInput = "";
        assertThrows(OptimusException.class, () -> event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_nullInput_exceptionThrown() {
        assertThrows(OptimusException.class, () -> event.parseStringEvent(null));
    }

    @Test
    public void parseStringEvent_invalidFormat_exceptionThrown() {
        String dateTimeInput = "2024-15-09T15:30";
        assertThrows(OptimusException.class, () -> event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_trailingWhitespace_expectSuccess() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30 ";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    @Test
    public void parseStringEvent_leadingWhitespace_expectSuccess() throws OptimusException {
        String dateTimeInput = " 15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }


    @Test
    public void eventConstructor_validFromToDates_expectSuccess() throws OptimusException {
        Event newEvent = new Event("New event", "15/09/2024 15:30", "16/09/2024 15:30");
        assertEquals("New event", newEvent.getDescription());
        assertEquals(LocalDateTime.of(2024, 9, 15, 15, 30), newEvent.parseStringEvent("15/09/2024 15:30"));
        assertEquals(LocalDateTime.of(2024, 9, 16, 15, 30), newEvent.parseStringEvent("16/09/2024 15:30"));
    }
}
