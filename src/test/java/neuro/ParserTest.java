package neuro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getDeadlineByIndex_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDeadlineByIndex(new String[]{}));
    }

    @Test
    public void getDeadlineByIndex_validInput_correctIndexReturned() {
        int index = 2;
        assertEquals(index, Parser.getDeadlineByIndex(new String[]{"test", "test", "/by", "test"}));
    }

    @Test
    public void getEventFromIndex_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getEventFromIndex(new String[]{}));
    }

    @Test
    public void getEventFromIndex_validInput_correctIndexReturned() {
        int index = 2;
        assertEquals(index, Parser.getEventFromIndex(new String[]{"test", "test", "/from", "test"}));
    }

    @Test
    public void getEventToIndex_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getEventToIndex(new String[]{}));
    }

    @Test
    public void getEventToIndex_validInput_correctIndexReturned() {
        int index = 2;
        assertEquals(index, Parser.getEventToIndex(new String[]{"test", "test", "/to", "test"}));
    }


    @Test
    public void parseDateTime_invalidInput_exceptionThrown() {
        String invalidInput = "This is an invalid date or time";
        assertThrows(IllegalArgumentException.class, () ->
                Parser.parseDateTime(invalidInput));
    }

    @Test
    public void parseDateTime_validInputForDate_localDateTimeObjectReturned() {
        String validDate = "30/07/2002";
        LocalDateTime expectedDateTime = LocalDateTime.of(2002, 7, 30, 0, 0);
        assertEquals(expectedDateTime, Parser.parseDateTime(validDate));
    }

    @Test
    public void parseDateTime_validInputForDateTime_localDateTimeObjectReturned() {
        String validDate = "30/07/2002 1500";
        LocalDateTime expectedDateTime = LocalDateTime.of(2002, 7, 30, 15, 0);
        assertEquals(expectedDateTime, Parser.parseDateTime(validDate));
    }
}
