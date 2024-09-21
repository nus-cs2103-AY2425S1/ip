package bob;

import bob.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeTest {
    // The accepted format for input is "d[d]/M[M][/uuuu][ HHmm]"
    // The format for output is "{dd-MMM-uuuu HHmm}"

    @Test
    public void parse_validFormat_success() {
        assertEquals(
                LocalDateTime.of(2024, 11, 12, 18, 30),
                DateTime.parse("12/11/2024 1830"));
        assertEquals(
                LocalDateTime.of(2023, 12, 1, 19, 30),
                DateTime.parse("01/12/2023 1930"));
        assertEquals(
                LocalDateTime.of(1999, 3, 5, 2, 0),
                DateTime.parse("05/03/1999 0200"));

        // Date and month with only 1 digit
        assertEquals(
                LocalDateTime.of(2019, 12, 1, 19, 30),
                DateTime.parse("1/12/2019 1930"));
        assertEquals(
                LocalDateTime.of(1719, 1, 12, 0, 0),
                DateTime.parse("12/1/1719 0000"));
        assertEquals(
                LocalDateTime.of(2019, 2, 4, 0, 0),
                DateTime.parse("4/2/2019 0000"));

        // Omitted year defaults to current year
        assertEquals(
                LocalDateTime.of(LocalDate.now().getYear(), 12, 1, 19, 30),
                DateTime.parse("1/12 1930"));

        // Omitted time defaults to 0000
        assertEquals(
                LocalDateTime.of(2019, 12, 1, 0, 0),
                DateTime.parse("1/12/2019"));

        // Omitted year and time defaults to current year and 0000
        assertEquals(
                LocalDateTime.of(LocalDate.now().getYear(), 12, 1, 0, 0),
                DateTime.parse("1/12"));
    }

    @Test
    public void parse_specialValues_success() {
        // Special value "now" returns current time
        assertEquals(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                DateTime.parse("now").truncatedTo(ChronoUnit.MINUTES));

        // Special value "tmr" and "tomorrow" returns a day later at current time
        assertEquals(
                LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.MINUTES),
                DateTime.parse("tmr").truncatedTo(ChronoUnit.MINUTES));
        assertEquals(
                LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.MINUTES),
                DateTime.parse("tomorrow").truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    public void parse_invalidFormatOrValues_exceptionThrown() {
        // Invalid format
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11-12-2019 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/2019 6:30PM"));

        // Invalid date
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("32/12/2019 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("00/12/2019 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("-1/12/2019 1830"));

        // Invalid month
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/00/2019 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/13/2019 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/-1/2019 1830"));

        // Invalid year
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/19 1830"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/222 1830"));

        // Invalid hour
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/2019 2430"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/2019 -130"));

        // Invalid minute
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/2019 1860"));
        assertThrows(InvalidDateTimeException.class, () -> DateTime.parse("11/12/2019 18-1"));
    }

    @Test
    public void format_success() {
        assertEquals("{11-Dec-2019 1830}",
                DateTime.format(LocalDateTime.of(2019, 12, 11, 18, 30)));
    }
}
