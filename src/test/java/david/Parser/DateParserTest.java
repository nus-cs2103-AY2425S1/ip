package david.Parser;

import david.Exceptions.DavidInvalidDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                assertThrows(DavidInvalidDateTimeException.class
                        ,() -> DateParser.getDate(" 2024-14-12 1300"));
        String expectedErrorMessage = "The inputted date time format is wrong. " +
                "Please ensure that it is in the format of" +
                "\"YYYY-MM-DD HHHH\"" + "where Y is the year, M is the month, D is the date and H" +
                "is the 24 hour time.";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when there month is not valid");
    }

    @Test
    public void invalidTimeExceptionThrown() {
        DavidInvalidDateTimeException exception =
                assertThrows(DavidInvalidDateTimeException.class
                        ,() -> DateParser.getDate(" 2024-11-12 2500"));
        String expectedErrorMessage = "The inputted date time format is wrong. " +
                "Please ensure that it is in the format of" +
                "\"YYYY-MM-DD HHHH\"" + "where Y is the year, M is the month, D is the date and H" +
                "is the 24 hour time.";
        assertEquals(expectedErrorMessage, exception.showErrorMessage(),
                "Exception is thrown when time is not valid");
    }

    @Test
    public void properOutputDateTimeFormat() throws DavidInvalidDateTimeException {
        LocalDateTime dateTime = DateParser.getDate(" 2024-12-12 1300");
        String actual = DateParser.formatOutputDate(dateTime);
        assertEquals("1300 12 Dec 2024", actual,"Proper date time format");
    }
    @Test
    public void properCacheDateTimeFormat() throws DavidInvalidDateTimeException {
        LocalDateTime dateTime = DateParser.getDate(" 2024-12-12 1300");
        String actual = DateParser.formatCacheDate(dateTime);
        assertEquals(" 2024-12-12 1300", actual,"Proper date time format");
    }
}
