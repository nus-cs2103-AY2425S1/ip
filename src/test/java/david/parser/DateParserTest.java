package david.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import david.exceptions.DavidInvalidDateTimeException;
import david.exceptions.DavidInvalidTimeException;

public class DateParserTest {

    @Test
    public void properDateTimeFormat() throws DavidInvalidDateTimeException {
        LocalDateTime actual = DateParser.getDate(" 2024-12-12 1300");
        assertEquals(LocalDateTime.of(2024, 12, 12, 13, 0), actual,
                "Proper date time format");
    }

    @Test
    public void invalidMonthExceptionThrown() {
        DavidInvalidDateTimeException exception =
                assertThrows(DavidInvalidDateTimeException.class, (
                    ) -> DateParser.getDate(" 2024-14-12 1300"));
        String expectedErrorMessage = "The inputted date time format is wrong. "
                + "Please ensure that it is in the format of "
                + "\"YYYY-MM-DD HHHH\" "
                + "where Y is the year, M is the month, D is the date and H"
                + "is the 24 hour time.";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when there month is not valid");
    }

    @Test
    public void invalidTimeFormatExceptionThrown() {
        DavidInvalidDateTimeException exception =
                assertThrows(DavidInvalidDateTimeException.class, (
                ) -> DateParser.getDate(" 2024-11-12 2500"));
        String expectedErrorMessage = "The inputted date time format is wrong. "
                + "Please ensure that it is in the format of "
                + "\"YYYY-MM-DD HHHH\" "
                + "where Y is the year, M is the month, D is the date and H"
                + "is the 24 hour time.";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when time is not valid");
    }

    /**
     * This method tests if the first time passed is before the second time passed
     */
    @Test
    public void invalidTimeExceptionThrown() {
        String formatInputPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(formatInputPattern);
        LocalDateTime t1 = LocalDateTime.parse("2023-12-01 1200", inputFormatter);
        LocalDateTime t2 = LocalDateTime.parse("2024-12-01 1200", inputFormatter);

        DavidInvalidTimeException exception =
                assertThrows(DavidInvalidTimeException.class, (
                ) -> DateParser.validateDateTime(t2, t1));
        String expectedErrorMessage = "Please ensure that the time inputted is after the current time."
                + " If you are trying to input an event task, make sure \"/from\" field is a valid time "
                + "before  \"/to\" field";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when t1 is before t2");
    }

    /**
     * This method tests if either time passed is before the current time
     */
    @Test
    public void validTimeWhenInputBeforeCurrentTime() throws DavidInvalidTimeException {
        String formatInputPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(formatInputPattern);
        LocalDateTime time = LocalDateTime.parse("2022-12-01 1200", inputFormatter);

        Boolean validTime = DateParser.validateDateTime(time, LocalDateTime.now());

        assertEquals(validTime, true);
    }

    @Test
    public void invalidTimeExceptionThrownWhenTimeSameAsInput() {
        String formatInputPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(formatInputPattern);
        LocalDateTime t1 = LocalDateTime.parse("2024-12-01 1200", inputFormatter);
        LocalDateTime t2 = LocalDateTime.parse("2024-12-01 1200", inputFormatter);

        DavidInvalidTimeException exception =
                assertThrows(DavidInvalidTimeException.class, (
                ) -> DateParser.validateDateTime(t2, t1));
        String expectedErrorMessage = "Please ensure that the time inputted is after the current time."
                + " If you are trying to input an event task, make sure \"/from\" field is a valid time "
                + "before  \"/to\" field";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when t1 is before t2");
    }

    @Test
    public void validTimeOutput() throws DavidInvalidTimeException {
        String formatInputPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(formatInputPattern);
        LocalDateTime t1 = LocalDateTime.parse("2023-12-01 1200", inputFormatter);
        LocalDateTime t2 = LocalDateTime.parse("2024-12-01 1200", inputFormatter);

        boolean validTime = DateParser.validateDateTime(t1, t2);
        assertEquals(validTime, true);
    }


    @Test
    public void properOutputDateTimeFormat() throws DavidInvalidDateTimeException {
        LocalDateTime dateTime = DateParser.getDate(" 2024-12-12 1300");
        String actual = DateParser.formatOutputDate(dateTime);
        assertEquals("1300 12 Dec 2024", actual, "Proper date time format");
    }
    @Test
    public void properCacheDateTimeFormat() throws DavidInvalidDateTimeException {
        LocalDateTime dateTime = DateParser.getDate(" 2024-12-12 1300");
        String actual = DateParser.formatCacheDate(dateTime);
        assertEquals(" 2024-12-12 1300", actual, "Proper date time format");
    }
}
