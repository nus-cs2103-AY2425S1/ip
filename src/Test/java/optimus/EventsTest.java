package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Event} class, focusing on the parsing of event date strings
 * and the validation of event-related inputs.
 */
//GPT helped with refining the tests as well as documentation reducing time needed overall
class EventsTest {

    private Event event;

    /**
     * Initializes the test with a sample {@link Event} object before each test.
     *
     * @throws OptimusException if there is an error during event creation.
     */
    @BeforeEach
    public void setUp() throws OptimusException {
        event = new Event("Test event", "1/09/2024 12:00", "1/09/2024 14:00");
    }

    /**
     * Tests parsing of valid date-time string in "D/M/yyyy HH:mm" format.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_validFormatSlashDmy_expectSuccess() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests parsing of valid date-time string in "yyyy-MM-dd HH:mm" format.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_validFormatHyphenYmd_expectSuccess() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests parsing of valid date-time string in "D-MM-yyyy HH:mm" format.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_validFormatHyphenDmy_expectSuccess() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests parsing of valid date-time string in "yyyy/MM/dd HH:mm" format.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_validFormatSlashYmd_expectSuccess() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests handling of an empty string input for date-time.
     * Expects an {@link OptimusException} to be thrown.
     */
    @Test
    public void parseStringEvent_emptyInput_exceptionThrown() {
        String dateTimeInput = "";
        assertThrows(OptimusException.class, () -> event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests handling of a null input for date-time.
     * Expects an {@link OptimusException} to be thrown.
     */
    @Test
    public void parseStringEvent_nullInput_exceptionThrown() {
        assertThrows(OptimusException.class, () -> event.parseStringEvent(null));
    }

    /**
     * Tests handling of an invalid date-time format.
     * Expects an {@link OptimusException} to be thrown.
     */
    @Test
    public void parseStringEvent_invalidFormat_exceptionThrown() {
        String dateTimeInput = "2024-15-09T15:30";
        assertThrows(OptimusException.class, () -> event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests parsing of a date-time string with trailing whitespace.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_trailingWhitespace_expectSuccess() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30 ";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests parsing of a date-time string with leading whitespace.
     *
     * @throws OptimusException if the parsing fails unexpectedly.
     */
    @Test
    public void parseStringEvent_leadingWhitespace_expectSuccess() throws OptimusException {
        String dateTimeInput = " 15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, event.parseStringEvent(dateTimeInput));
    }

    /**
     * Tests creation of an {@link Event} object with valid from/to dates.
     *
     * @throws OptimusException if the event creation or parsing fails unexpectedly.
     */
    @Test
    public void eventConstructor_validFromToDates_expectSuccess() throws OptimusException {
        Event newEvent = new Event("New event", "15/09/2024 15:30", "16/09/2024 15:30");
        assertEquals("New event", newEvent.getDescription());
        assertEquals(LocalDateTime.of(2024, 9, 15, 15, 30), newEvent.parseStringEvent("15/09/2024 15:30"));
        assertEquals(LocalDateTime.of(2024, 9, 16, 15, 30), newEvent.parseStringEvent("16/09/2024 15:30"));
    }
}
