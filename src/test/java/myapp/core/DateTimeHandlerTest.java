package myapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


class DateTimeHandlerTest {

    @Test
    void parse_validDateTimeString_returnsLocalDateTime() {
        String validDateTime = "20/09/2023 2359";
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 9, 20, 23, 59);
        LocalDateTime actualDateTime = DateTimeHandler.parse(validDateTime);
        assertEquals(expectedDateTime, actualDateTime, "Parsed LocalDateTime should match expected value.");
    }

    @Test
    void parse_invalidDateTimeString_throwsDateTimeParseException() {
        String invalidDateTime = "invalid_date";
        assertThrows(DateTimeParseException.class, () -> DateTimeHandler.parse(invalidDateTime),
                "Expected DateTimeParseException for invalid date time string.");
    }

    @Test
    void parseDate_validDateString_returnsLocalDate() {
        String validDate = "20/09/2023";
        LocalDate expectedDate = LocalDate.of(2023, 9, 20);
        LocalDate actualDate = DateTimeHandler.parseDate(validDate);
        assertEquals(expectedDate, actualDate, "Parsed LocalDate should match expected value.");
    }

    @Test
    void parseDate_invalidDateString_throwsDateTimeParseException() {
        String invalidDate = "invalid_date";
        assertThrows(DateTimeParseException.class, () -> DateTimeHandler.parseDate(invalidDate),
                "Expected DateTimeParseException for invalid date string.");
    }

    @Test
    void format_localDateTimeWithFileFormat_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 20, 23, 59);
        String expectedFormatted = "20/9/2023 2359";
        String actualFormatted = DateTimeHandler.format(dateTime, true);
        assertEquals(expectedFormatted, actualFormatted, "Formatted LocalDateTime should match expected file format.");
    }

    @Test
    void format_localDateTimeWithoutFileFormat_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 20, 23, 59);
        String expectedFormatted = "20 Sep 2023, 11:59 PM";
        String actualFormatted = DateTimeHandler.format(dateTime, false);
        assertEquals(expectedFormatted, actualFormatted,
                "Formatted LocalDateTime should match expected output format.");
    }

    @Test
    void format_localDateTimeUsingDefaultFormat_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 20, 23, 59);
        String expectedFormatted = "20 Sep 2023, 11:59 PM";
        String actualFormatted = DateTimeHandler.format(dateTime);
        assertEquals(expectedFormatted, actualFormatted,
                "Formatted LocalDateTime should match default output format.");
    }

    @Test
    void format_localDate_returnsFormattedString() {
        LocalDate date = LocalDate.of(2023, 9, 20);
        String expectedFormatted = "20 Sep 2023";
        String actualFormatted = DateTimeHandler.format(date);
        assertEquals(expectedFormatted, actualFormatted, "Formatted LocalDate should match expected output format.");
    }
}
