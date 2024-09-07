package duck.common;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;






/**
 * Tests for the {@link Utils} class to verify the conversion of strings to {@link LocalDateTime}.
 */
public class UtilsTest {

    /**
     * Tests if {@link Utils#convertToDateTime(String)} correctly parses valid date-time strings.
     * This test checks both accepted formats: "yyyy-MM-dd HHmm" and "yyyy/MM/dd HHmm".
     *
     * @throws DuckException If there is an issue with the Duck-specific exception handling.
     */
    @Test
    public void convertToDateTime_correctStringFormat_success() throws DuckException {
        String dateTime1 = "2021-08-21 1800";
        String dateTime2 = "2021/08/21 1700";

        // Test valid date-time in "yyyy-MM-dd HHmm" format
        assertDoesNotThrow(() -> Utils.convertToDateTime(dateTime1));
        assertEquals("2021-08-21T18:00", Utils.convertToDateTime(dateTime1).toString());

        // Test valid date-time in "yyyy/MM/dd HHmm" format
        assertDoesNotThrow(() -> Utils.convertToDateTime(dateTime2));
        assertEquals("2021-08-21T17:00", Utils.convertToDateTime(dateTime2).toString());
    }

    /**
     * Tests if {@link Utils#convertToDateTime(String)} throws {@link DuckException} for invalid date-time strings.
     * This includes strings with incorrect formats or out-of-range values.
     */
    @Test
    public void convertToDateTime_incorrectStringFormat_exceptionThrown() {
        String[] incorrectDateTimes = {
            "2021-08-21 18000", // Incorrect minute format (five digits)
            "2021-08-21 1860", // Incorrect minute format (60 minutes)
            "2021-08-21 18:00", // Incorrect format (includes colon)
            "2021-08-21 18:00:00", // Incorrect format (includes seconds)
            "2021-08/21 1800", // Incorrect separator (slash in date)
            "20210821 1800", // Incorrect format (missing separator)
            "2021-08-21", // Incorrect format (missing time)
            "20210821" // Incorrect format (missing separators and time)
        };

        // Verify that each incorrect date-time format throws an exception
        for (String dateTime : incorrectDateTimes) {
            assertThrows(DuckException.class, () -> Utils.convertToDateTime(dateTime));
        }
    }
}
