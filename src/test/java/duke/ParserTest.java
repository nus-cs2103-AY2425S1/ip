package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseDateValid() { // test parse date
        LocalDate date = Parser.parseDateTime("2024-08-28");
        assertEquals(LocalDate.of(2024, 8, 28), date, "Parsed date should match the expected date.");
    }

    @Test
    public void testParseDateInvalid() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseDateTime("28-08-2024");
        }, "Parsing an invalid date format should throw DateTimeParseException.");
    }

    @Test
    public void testParseDateInvalidFormat() {
        // Test for invalid date format
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseDateTime("28-08-2024");
        }, "Invalid date format should throw DateTimeException.");
    }

    @Test
    public void testParseDateNullInput() {
        // Test for null input
        assertThrows(NullPointerException.class, () -> {
            Parser.parseDateTime(null);
        }, "Null input should throw NullPointerException.");
    }

    @Test
    public void testParseDateEmptyInput() {
        // Test for empty input
        assertThrows(DateTimeException.class, () -> {
            Parser.parseDateTime("");
        }, "Empty input should throw DateTimeException.");
    }
}