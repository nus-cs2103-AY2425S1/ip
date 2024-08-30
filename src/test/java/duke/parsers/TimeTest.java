package duke.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exceptions.InvalidDateException;

public class TimeTest {
    @Test
    public void testParseDateTime_ValidDateTime() throws InvalidDateException {
        String validDateTime = "01/09/2024 15:30";
        Time time = new Time(validDateTime);
        assertEquals(validDateTime, time.toString());
    }

    @Test
    public void testParseDateTime_ValidDate() throws InvalidDateException {
        String validDate = "01/09/2024";
        Time time = new Time(validDate);
        assertEquals(validDate, time.toString());
    }

    @Test
    public void testParseDateTime_InvalidDateTime() {
        String invalidDate = "2024/09/01 15:30";
        assertThrows(InvalidDateException.class, () -> new Time(invalidDate));
    }

    @Test
    public void testParseDateTime_InvalidDate() {
        String invalidFormat = "01-09-2024"; // Incorrect separators
        assertThrows(InvalidDateException.class, () -> new Time(invalidFormat));
    }
}
