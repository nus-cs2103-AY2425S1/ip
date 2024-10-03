package duck.common;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;

/**
 * Tests the {@link Utils} class to verify the conversion of strings to {@link LocalDateTime}.
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

    /**
     * Tests if {@link Utils#isCorrectUpdateFormat(String)} correctly identifies invalid update command formats.
     */
    @Test
    public void isCorrectUpdateFormat_incorrectFormat_returnFalse() {
        String incorrect1 = "mark";
        String incorrect2 = "mark haha";
        String incorrect3 = "delete 1 2";
        String incorrect4 = "unmark 1.0";

        assertFalse(Utils.isCorrectUpdateFormat(incorrect1));
        assertFalse(Utils.isCorrectUpdateFormat(incorrect2));
        assertFalse(Utils.isCorrectUpdateFormat(incorrect3));
        assertFalse(Utils.isCorrectUpdateFormat(incorrect4));

    }

    /**
     * Tests if {@link Utils#isCorrectUpdateFormat(String)} correctly identifies valid update command formats.
     */
    @Test
    public void isCorrectUpdateFormat_correctFormat_returnTrue() {
        String correct1 = "mark 1";
        String correct2 = "delete 2";
        String correct3 = "unmark 3";

        assertTrue(Utils.isCorrectUpdateFormat(correct1));
        assertTrue(Utils.isCorrectUpdateFormat(correct2));
        assertTrue(Utils.isCorrectUpdateFormat(correct3));
    }


    /**
     * Tests if {@link Utils#isInteger(String)} correctly identifies valid integers.
     */
    @Test
    public void isInteger_isInteger_returnTrue() {
        String integer1 = "1";
        String integer2 = "0";
        String integer3 = "-1";

        assertTrue(Utils.isInteger(integer1));
        assertTrue(Utils.isInteger(integer2));
        assertTrue(Utils.isInteger(integer3));
    }

    /**
     * Tests if {@link Utils#isInteger(String)} correctly identifies invalid integers.
     */
    @Test
    public void isInteger_notInteger_exceptionThrown() {
        String notInteger1 = "1.0";
        String notInteger2 = "a";
        String notInteger3 = "1a";

        assertFalse(Utils.isInteger(notInteger1));
        assertFalse(Utils.isInteger(notInteger2));
        assertFalse(Utils.isInteger(notInteger3));
    }

    /**
     * Tests if {@link Utils#getTaskIndex(String)} correctly retrieves the task index from a valid string.
     */
    @Test
    public void getTaskIndex_validIndex_returnIndex() {
        String index1 = "mark 1";
        String index2 = "unmark 2";
        String index3 = "delete 3";

        assertDoesNotThrow(() -> Utils.getTaskIndex(index1));
        assertDoesNotThrow(() -> Utils.getTaskIndex(index2));
        assertDoesNotThrow(() -> Utils.getTaskIndex(index3));
        try {
            Utils.getTaskIndex(index1);
            Utils.getTaskIndex(index2);
            Utils.getTaskIndex(index3);
        } catch (DuckException e) {
            fail("Exception should not be thrown.");
        }
    }

    /**
     * Tests if {@link Utils#getTaskIndex(String)} throws an exception for an invalid task index.
     */
    @Test
    public void getTaskIndex_invalidIndex_exceptionThrown() {
        String invalidIndex1 = "test 1.0";
        String invalidIndex2 = "test a";
        String invalidIndex3 = "test 1a";
        String invalidIndex4 = "test";

        assertThrows(DuckException.class, () -> Utils.getTaskIndex(invalidIndex1));
        assertThrows(DuckException.class, () -> Utils.getTaskIndex(invalidIndex2));
        assertThrows(DuckException.class, () -> Utils.getTaskIndex(invalidIndex3));
        assertThrows(DuckException.class, () -> Utils.getTaskIndex(invalidIndex4));
    }

    /**
     * Tests if {@link Utils#trimExtraSpaces(String)} correctly removes extra spaces from a string.
     */
    @Test
    public void trimExtraSpaces_extraSpaces_removed() {
        String input = "  test  string  ";
        String expected = "test string";
        assertEquals(expected, Utils.trimExtraSpaces(input));
    }

}
