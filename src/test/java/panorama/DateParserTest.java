package panorama; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import panorama.exception.InvalidDateException;

public class DateParserTest {
    @Test
    public void parse_validDate_success() {
        String dateStr = "2020-08-01";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        assertDoesNotThrow(() -> {
            LocalDate expected = LocalDate.parse(dateStr, formatter);
            assertEquals(expected, DateParser.parse(dateStr));
        });
    }

    @Test
    public void parse_invalidDate_exceptionThrown() {
        String dateStr = "abcdef";

        InvalidDateException e = assertThrows(InvalidDateException.class, () -> {
            DateParser.parse(dateStr);
        });

        assertEquals("Invalid date specified.", e.getMessage());
    }
}
