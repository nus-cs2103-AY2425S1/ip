package utility;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;



public class ParserTest {
    @Test
    public void parseInputDate_validDate_success() {
        String inputDate = "2024-08-31 1243";
        String expectedOutputDate = "31 Aug 2024 1243";
        assertEquals(expectedOutputDate, Parser.parseInputDate(inputDate));
    }

    @Test
    public void parseInputDate_invalidDate_exceptionThrown() {
        String invalidDate = "2024-08-31 12:43"; // invalid ':' between HH and mm
        assertThrows(DateTimeParseException.class, () -> Parser.parseInputDate(invalidDate));
    }
}
