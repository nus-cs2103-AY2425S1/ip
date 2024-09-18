package echoa;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    private static final String DATE_REGEX = "\\b(\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))\\b";

    private static String findDate(String s) {
        String date = null;
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            date = matcher.group(1); // Capture the full date match
        }
        return date;
    }

    @Test
    void testFindDate_ValidDate() {
        String input = "2024-09-18";
        String expectedDate = "2024-09-18";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testFindDate_InvalidDate() {
        String input = "2024-13-01";
        String actualDate = findDate(input);
        assertNull(actualDate);
    }

    @Test
    void testFindDate_DateInString() {
        String input = "2024-09-18 12:00";
        String expectedDate = "2024-09-18";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testFindDateNoDate() {
        String input = "1";
        String actualDate = findDate(input);
        assertNull(actualDate);
    }

    @Test
    void testFindDateEmptyString() {
        String input = "";
        String actualDate = findDate(input);
        assertNull(actualDate);
    }


    private static final String TIME_REGEX = "\\b(?:[01]\\d|2[0-3]):[0-5]\\d\\b";

    private static String findTime(String s) {
        String time = null;
        Pattern pattern = Pattern.compile(TIME_REGEX);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            time = matcher.group();
        }
        return time;
    }

    @Test
    public void testFindTime_ValidTime() {
        String input = "02:30";
        String expected = "02:30";
        assertEquals(expected, findTime(input));
    }

    @Test
    public void testFindTime_InvalidTime() {
        String input = "The time is 25:00";
        assertNull(findTime(input));
    }

    @Test
    public void testFindTime_TimeInString() {
        String input = "2024-09-18 12:00";
        String expected = "12:00";
        assertEquals(expected, findTime(input));
    }

    @Test
    public void testFindTime_NoTime() {
        String input = "1";
        assertNull(findTime(input));
    }

    @Test
    public void testFindTime_EmptyString() {
        String input = "";
        assertNull(findTime(input));
    }
}
