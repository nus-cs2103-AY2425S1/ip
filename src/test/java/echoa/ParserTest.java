package echoa;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    // Utility class to hold the method
    private static class StringUtils {
        private static String removeFirstOccurrence(String str, char letter) {
            int index = str.indexOf(letter);
            if (index == -1) {
                return str;
            }
            return str.substring(0, index) + str.substring(index + 1);
        }
    }
    @Test
    public void testRemoveFirstOccurrence_charExists() {
        String result = StringUtils.removeFirstOccurrence("hello", 'l');
        assertEquals("helo", result, "Character 'l' should be removed from 'hello'");
    }

    @Test
    public void testRemoveFirstOccurrence_charDoesNotExist() {
        String result = StringUtils.removeFirstOccurrence("hello", 'x');
        assertEquals("hello", result, "String should remain unchanged when character does not exist");
    }

    @Test
    public void testRemoveFirstOccurrence_emptyString() {
        String result = StringUtils.removeFirstOccurrence("", 'a');
        assertEquals("", result, "Empty string should remain unchanged regardless of the character");
    }

    @Test
    public void testRemoveFirstOccurrence_charOccursMultipleTimes() {
        String result = StringUtils.removeFirstOccurrence("ball", 'l');
        assertEquals("bal", result, "Only the first occurrence of 'l' should be removed from 'ball'");
    }

    @Test
    public void testRemoveFirstOccurrence_charAtStart() {
        String result = StringUtils.removeFirstOccurrence("apple", 'a');
        assertEquals("pple", result, "Character 'a' at the start of 'apple' should be removed");
    }

    @Test
    public void testRemoveFirstOccurrence_charAtEnd() {
        String result = StringUtils.removeFirstOccurrence("banana", 'a');
        assertEquals("bnana", result, "Only the first occurrence of 'a' should be removed from 'banana'");
    }

    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    private static String findDate(String s) {
        String date = null;
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            date = matcher.group();
        }
        return date;
    }

    @Test
    void testFindDateValidDate() {
        String input = "The event is scheduled for 2024-09-18.";
        String expectedDate = "2024-09-18";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate, "The extracted date should be 2024-09-18.");
    }

    @Test
    void testFindDateNoDate() {
        String input = "There is no date in this string.";
        String actualDate = findDate(input);
        assertNull(actualDate, "The extracted date should be null if no date is found.");
    }

    @Test
    void testFindDateMultipleDates() {
        String input = "The first event is on 2024-09-18 and the second event is on 2024-10-05.";
        String expectedDate = "2024-09-18"; // Assuming it returns the first match
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate, "The extracted date should be the first date found, 2024-09-18.");
    }

    @Test
    void testFindDateInvalidDateFormat() {
        String input = "The date is 2024-13-01 which is invalid.";
        String actualDate = findDate(input);
        assertNull(actualDate, "The extracted date should be null if the date format is invalid.");
    }

    @Test
    void testFindDateDateAtStart() {
        String input = "2024-09-18 is the event date.";
        String expectedDate = "2024-09-18";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate, "The extracted date should be 2024-09-18.");
    }

    @Test
    void testFindDateDateAtEnd() {
        String input = "The event is scheduled for the last day of the month, 2024-09-30.";
        String expectedDate = "2024-09-30";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate, "The extracted date should be 2024-09-30.");
    }

    @Test
    void testFindDateDateWithExtraSpaces() {
        String input = "The important date is  2024-09-18  .";
        String expectedDate = "2024-09-18";
        String actualDate = findDate(input);
        assertEquals(expectedDate, actualDate, "The extracted date should be 2024-09-18.");
    }

    private static final String TIME_REGEX = "\\b([01]\\d|2[0-3]):[0-5]\\d\\b";

    // Utility class to hold the method
    private static class TimeUtils {
        private static String findTime(String s) {
            String time = null;
            Pattern pattern = Pattern.compile(TIME_REGEX);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                time = matcher.group();
            }
            return time;
        }
    }

    @Test
    public void testFindTime_validTime() {
        String input = "The meeting is at 02:30";
        String expected = "02:30";
        assertEquals(expected, TimeUtils.findTime(input), "Should find the time '02:30'");
    }

    @Test
    public void testFindTime_multipleTimes() {
        String input = "Times are 09:00 AM and 03:45 PM.";
        String expected = "03:45 AM"; // Assuming the regex finds the first occurrence
        assertEquals(expected, TimeUtils.findTime(input), "Should find the first time '03:45'");
    }

    @Test
    public void testFindTime_noTime() {
        String input = "There is no time in this string.";
        assertNull(TimeUtils.findTime(input), "Should return null when no time is present");
    }

    @Test
    public void testFindTime_emptyString() {
        String input = "";
        assertNull(TimeUtils.findTime(input), "Should return null for an empty string");
    }

    @Test
    public void testFindTime_invalidTimeFormat() {
        String input = "The time is 25:00 or 13:61.";
        assertNull(TimeUtils.findTime(input), "Should return null for invalid time formats");
    }

    @Test
    public void testFindTime_duplicateTime() {
        String input = "Times like 12:00 and 12:00";
        String expected = "12:00";
        assertEquals(expected, TimeUtils.findTime(input), "Should find the time '12:00'");
    }
}
