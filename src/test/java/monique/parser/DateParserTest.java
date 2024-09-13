package monique.parser;
import static monique.parser.DateParser.getDateTimeString;
import static monique.parser.DateParser.getNextDateOfWeek;
import static monique.parser.DateParser.hasTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import monique.exception.IllegalDateFormatException;

public class DateParserTest {

    @BeforeAll
    public static void setUp() {
        Locale.setDefault(new Locale("en", "SG"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }
    @Test
    void testGetTimeString() {
        assertEquals("1700", DateParser.getTimeString("11/9/2024 1700"));
        assertEquals("17:00", DateParser.getTimeString("11/9/2024 17:00"));
        assertEquals("6pm", DateParser.getTimeString("11/9/2024 6pm"));
        assertEquals("10am", DateParser.getTimeString("11/9/2024 10am"));
        assertEquals("1115am", DateParser.getTimeString("11/9/2024 1115am"));

        assertEquals("1700", DateParser.getTimeString("mon 1700"));
        assertEquals("17:00", DateParser.getTimeString("Monday 17:00"));
        assertEquals("6pm", DateParser.getTimeString("tue 6pm"));
        assertEquals("10am", DateParser.getTimeString("Tuesday 10am"));
        assertEquals("1115am", DateParser.getTimeString("tomorrow 1115am"));
        assertEquals("1115am", DateParser.getTimeString("Tomorrow 1115am"));

        // Additional test cases
        assertEquals("12pm", DateParser.getTimeString("12/25/2024 12pm"));
        assertEquals("12:00", DateParser.getTimeString("12/25/2024 12:00"));
        assertEquals("1230pm", DateParser.getTimeString("12/25/2024 1230pm"));

        // Testing different days and time formats
        assertEquals("0800", DateParser.getTimeString("wed 0800"));
        assertEquals("08:00", DateParser.getTimeString("Wednesday 08:00"));
        assertEquals("5pm", DateParser.getTimeString("thu 5pm"));
        assertEquals("5:00pm", DateParser.getTimeString("Thursday 5:00pm"));
        assertEquals("645am", DateParser.getTimeString("fri 645am"));
        assertEquals("6:45am", DateParser.getTimeString("Friday 6:45am"));
        assertEquals("1030pm", DateParser.getTimeString("sat 1030pm"));
        assertEquals("10:30pm", DateParser.getTimeString("Saturday 10:30pm"));
        assertEquals("0830", DateParser.getTimeString("sun 0830"));
        assertEquals("08:30", DateParser.getTimeString("Sunday 08:30"));

        // Testing different formats with separators
        assertEquals("22:00", DateParser.getTimeString("11/9/2024 22:00"));
        assertEquals("0000", DateParser.getTimeString("01/01/2024 0000"));
        assertEquals("00:00", DateParser.getTimeString("01/01/2024 00:00"));
        assertEquals("1159pm", DateParser.getTimeString("01/01/2024 1159pm"));
    }


    @Test
    void testGetDateString() throws IllegalDateFormatException {
        assertEquals("11/9/2024", DateParser.getDateString("11/9/2024 17:00"));
        assertEquals("11/9/2024", DateParser.getDateString("11/9/2024 6pm"));
        assertEquals("11/9/2024", DateParser.getDateString("11/9/2024 10am"));
        assertEquals("11/9/2024", DateParser.getDateString("11/9/2024 1115am"));

        assertEquals("11/12/2024", DateParser.getDateString("11/12/2024 1700"));
        assertEquals("1/9/2024", DateParser.getDateString("1/9/2024 17:00"));
        assertEquals("1/12/2024", DateParser.getDateString("1/12/2024 6pm"));
        assertEquals("01/09/2024", DateParser.getDateString("01/09/2024 10am"));

        assertEquals("11-9-2024", DateParser.getDateString("11-9-2024 17:00"));
        assertEquals("11-9-2024", DateParser.getDateString("11-9-2024 6pm"));
        assertEquals("11-9-2024", DateParser.getDateString("11-9-2024 10am"));
        assertEquals("11-9-2024", DateParser.getDateString("11-9-2024 1115am"));

        assertEquals("11-12-2024", DateParser.getDateString("11-12-2024 1700"));
        assertEquals("1-9-2024", DateParser.getDateString("1-9-2024 17:00"));
        assertEquals("1-12-2024", DateParser.getDateString("1-12-2024 6pm"));
        assertEquals("01-09-2024", DateParser.getDateString("01-09-2024 10am"));


        assertEquals("mon", DateParser.getDateString("mon 1700"));
        assertEquals("Monday", DateParser.getDateString("Monday 17:00"));
        assertEquals("tue", DateParser.getDateString("tue 6pm"));
        assertEquals("Tuesday", DateParser.getDateString("Tuesday 10am"));
        assertEquals("tomorrow", DateParser.getDateString("tomorrow 1115am"));
        assertEquals("Tomorrow", DateParser.getDateString("Tomorrow 1115am"));

        // Test cases for day abbreviations and full day names
        assertEquals("wed", DateParser.getDateString("wed 1530"));
        assertEquals("Wednesday", DateParser.getDateString("Wednesday 15:30"));
        assertEquals("thu", DateParser.getDateString("thu 8pm"));
        assertEquals("Thursday", DateParser.getDateString("Thursday 8pm"));
        assertEquals("fri", DateParser.getDateString("fri 1145pm"));
        assertEquals("Friday", DateParser.getDateString("Friday 11:45pm"));
        assertEquals("sat", DateParser.getDateString("sat 9am"));
        assertEquals("Saturday", DateParser.getDateString("Saturday 9am"));
        assertEquals("sun", DateParser.getDateString("sun 7pm"));
        assertEquals("Sunday", DateParser.getDateString("Sunday 7pm"));

        // Additional variations of "tomorrow"
        assertEquals("tomorrow", DateParser.getDateString("tomorrow 7pm"));
        assertEquals("Tomorrow", DateParser.getDateString("Tomorrow 7pm"));

        // Testing edge cases and mixed cases
        assertEquals("Mon", DateParser.getDateString("Mon 1800"));
        assertEquals("MON", DateParser.getDateString("MON 18:00"));
        assertEquals("tue", DateParser.getDateString("tue 7 am"));
        assertEquals("TUE", DateParser.getDateString("TUE 7am"));
        assertEquals("TOMORROW", DateParser.getDateString("TOMORROW 1145pm"));
        assertEquals("ToMoRRoW", DateParser.getDateString("ToMoRRoW 1145PM"));
    }

    @Test
    void testParseDateValidSlashFormat() throws IllegalDateFormatException {
        assertEquals(LocalDate.of(2024, 11, 9), DateParser.parseDate("11/9/2024"));
        assertEquals(LocalDate.of(2024, 1, 31), DateParser.parseDate("1/31/2024"));
    }

    @Test
    void testParseDateValidDashFormat() throws IllegalDateFormatException {
        assertEquals(LocalDate.of(2024, 11, 9), DateParser.parseDate("11-9-2024"));
        assertEquals(LocalDate.of(2024, 1, 31), DateParser.parseDate("1-31-2024"));
    }

    @Test
    void testParseDateInvalidFormat() {
        assertThrows(IllegalDateFormatException.class, () -> DateParser.parseDate("2024/11/09"));
        assertThrows(IllegalDateFormatException.class, () -> DateParser.parseDate("2024-31-01"));
        assertThrows(IllegalDateFormatException.class, () -> DateParser.parseDate("31/01/2024"));
    }

    @Test
    void testParseDateEdgeCases() throws IllegalDateFormatException {
        // Testing with leading zeros
        assertEquals(LocalDate.of(2024, 11, 9), DateParser.parseDate("11/09/2024"));
        assertEquals(LocalDate.of(2024, 01, 31), DateParser.parseDate("01/31/2024"));

        // Testing single digit months and days
        assertEquals(LocalDate.of(2024, 2, 5), DateParser.parseDate("2/5/2024"));
        assertEquals(LocalDate.of(2024, 2, 5), DateParser.parseDate("2-5-2024"));
    }


    // Helper method to calculate expected date for day of the week
    private LocalDate getExpectedNextDate(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        int daysUntilNext = (dayOfWeek.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
        if (daysUntilNext == 0) {
            daysUntilNext = 7; // If it's today, get the next occurrence
        }
        return today.plusDays(daysUntilNext);
    }

    @Test
    void testParseDateOrDayMonday() throws IllegalDateFormatException {
        LocalDate expectedMonday = getExpectedNextDate(DayOfWeek.MONDAY);
        LocalDate actualMonday = DateParser.parseDateOrDay("Monday");
        assertEquals(expectedMonday, actualMonday);
    }

    @Test
    void testParseDateOrDayMon() throws IllegalDateFormatException {
        LocalDate expectedMonday = getExpectedNextDate(DayOfWeek.MONDAY);
        LocalDate actualMonday = DateParser.parseDateOrDay("Mon");
        assertEquals(expectedMonday, actualMonday);
    }

    @Test
    void testParseDateOrDayTomorrow() throws IllegalDateFormatException {
        LocalDate today = LocalDate.now();
        LocalDate expectedTomorrow = today.plusDays(1);
        LocalDate actualTomorrow = DateParser.parseDateOrDay("tomorrow");
        assertEquals(expectedTomorrow, actualTomorrow);
    }

    @Test
    void testParseDateOrDayDateFormat1() throws IllegalDateFormatException {
        LocalDate expectedDate = LocalDate.of(2024, 9, 11);
        LocalDate actualDate = DateParser.parseDateOrDay("9/11/2024");
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testParseDateOrDayDateFormat2() throws IllegalDateFormatException {
        LocalDate expectedDate = LocalDate.of(2024, 9, 11);
        LocalDate actualDate = DateParser.parseDateOrDay("09-11-2024");
        assertEquals(expectedDate, actualDate);
    }


    // Test for 24-hour format without colon
    @Test
    void testParseTime24HourNoColon() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(17, 0);
        LocalTime actualTime = DateParser.parseTime("1700");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 17:00");
    }

    // Test for 24-hour format with colon
    @Test
    void testParseTime24HourWithColon() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(17, 0);
        LocalTime actualTime = DateParser.parseTime("17:00");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 17:00");
    }

    // Test for 12-hour format with AM
    @Test
    void testParseTime12HourAM() throws IllegalDateFormatException {
        // Set locale and time zone to your actual settings
        LocalTime expectedTime = LocalTime.of(6, 0);
        LocalTime actualTime = DateParser.parseTime("6am");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 06:00 AM");
    }

    // Test for 12-hour format with PM
    @Test
    void testParseTime12HourPM() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(18, 0);
        LocalTime actualTime = DateParser.parseTime("6pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 18:00 PM");
    }

    // Test for 12-hour format with colon and AM
    @Test
    void testParseTime12HourWithColonAM() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(6, 30);
        LocalTime actualTime = DateParser.parseTime("6:30am");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 06:30 AM");
    }

    // Test for 12-hour format with colon and PM
    @Test
    void testParseTime12HourWithColonPM() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(18, 30);
        LocalTime actualTime = DateParser.parseTime("6:30pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 18:30 PM");
    }

    // Test for 12-hour format without colon and AM
    @Test
    void testParseTime12HourNoColonAM() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(7, 45);
        LocalTime actualTime = DateParser.parseTime("745am");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 07:45 AM");
    }

    // Test for 12-hour format without colon and PM
    @Test
    void testParseTime12HourNoColonPM() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(23, 15);
        LocalTime actualTime = DateParser.parseTime("1115pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 23:15 PM");
    }

    // Test for invalid time format
    @Test
    void testParseTimeInvalid() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("invalid-time");
        }, "Parsing an invalid time should throw IllegalArgumentException");
    }

    // Test for leading or trailing spaces in time string
    @Test
    void testParseTimeWithSpaces() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(14, 30);
        LocalTime actualTime = DateParser.parseTime(" 2:30 pm ");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 14:30 PM despite leading/trailing spaces");
    }

    @Test
    void testParseTime24HourSingleDigitHours() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(9, 30);
        LocalTime actualTime = DateParser.parseTime("0930");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 09:30");
    }

    @Test
    void testParseTime24HourWithTrailingSpaces() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(23, 0);
        LocalTime actualTime = DateParser.parseTime(" 2300 ");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 23:00 despite leading/trailing spaces");
    }

    @Test
    void testParseTime12HourWithLeadingZeros() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(19, 5);
        LocalTime actualTime = DateParser.parseTime("07:05pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 19:05 PM");
    }

    @Test
    void testParseTime24HourSingleDigitHourWithColon() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(8, 30);
        LocalTime actualTime = DateParser.parseTime("8:30");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 08:30");
    }

    @Test
    void testParseTime24HourLeadingZeros() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(8, 45);
        LocalTime actualTime = DateParser.parseTime("08:45");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 08:45");
    }

    @Test
    void testParseTime24HourInvalidHour() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("25:00");
        }, "Parsing a time with hours exceeding 23 should throw IllegalDateFormatException");
    }

    @Test
    void testParseTime24HourInvalidMinutes() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("14:60");
        }, "Parsing a time with invalid minutes should throw IllegalDateFormatException");
    }


    @Test
    void testParseTime12HourInvalidAmPm() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("12:00xm");
        }, "Parsing a time with an invalid AM/PM indicator should throw IllegalDateFormatException");
    }

    @Test
    void testParseTime24HourSingleDigitHourInvalidMinutes() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("9:90");
        }, "Parsing a time with single-digit hour and invalid minutes should throw IllegalDateFormatException");
    }

    @Test
    void testParseTime24HourZeroHour() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(0, 0);
        LocalTime actualTime = DateParser.parseTime("00:00");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 00:00");
    }

    @Test
    void testParseTime24HourMidnightNoColon() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(0, 0);
        LocalTime actualTime = DateParser.parseTime("0000");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 00:00");
    }
    @Test
    void testParseTime12HourWithSpaces() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(9, 45);
        LocalTime actualTime = DateParser.parseTime("  9:45 am  ");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 09:45 AM despite leading/trailing spaces");
    }

    @Test
    void testParseTime24HourExceedingHours() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("2500");
        }, "Parsing a time with hours exceeding 23 should throw IllegalDateFormatException");
    }

    @Test
    void testParseTimeThreeDigitHourFormat() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(21, 30);
        LocalTime actualTime = DateParser.parseTime("930pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 21:30 PM");
    }

    @Test
    void testParseTimeThreeDigitHourWithColon() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(21, 30);
        LocalTime actualTime = DateParser.parseTime("9:30pm");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 21:30 PM");
    }

    @Test
    void testParseTimeThreeDigitHourWithColonLeadingZero() throws IllegalDateFormatException {
        LocalTime expectedTime = LocalTime.of(9, 30);
        LocalTime actualTime = DateParser.parseTime("09:30am");
        assertEquals(expectedTime, actualTime, "The time should be parsed as 09:30 AM");
    }

    @Test
    void testParseTimeInvalidMinutes() {
        assertThrows(IllegalDateFormatException.class, () -> {
            DateParser.parseTime("11:60pm");
        }, "Parsing a time with invalid minutes should throw IllegalDateFormatException");
    }

    @Test
    public void testGetDateTimeStringWithDateOnly() throws IllegalDateFormatException {
        String input = "09-15-2024";
        LocalDateTime expected = LocalDateTime.of(LocalDate.parse("2024-09-15"), LocalTime.MIDNIGHT);
        assertEquals(expected, getDateTimeString(input));
    }

    @Test
    public void testHasTimeWithDateAndTime() {
        String input = "2-9-2024 11:15pm";
        assertTrue(hasTime(input), "Expected 'hasTime' to return true for input with time component.");
    }

    @Test
    public void testGetDateTimeStringWithDateAndTime() throws IllegalDateFormatException {
        String input = "2-9-2024 11:15pm";
        LocalDateTime expected = LocalDateTime.of(LocalDate.parse("2024-02-09"), LocalTime.parse("23:15"));
        assertEquals(expected, getDateTimeString(input));
    }

    @Test
    public void testGetDateTimeStringWithDayOfWeekOnly() throws IllegalDateFormatException {
        String input = "Monday";
        LocalDate today = LocalDate.now();
        LocalDate expected = getNextDateOfWeek(java.time.DayOfWeek.MONDAY);
        LocalDateTime expectedDateTime = LocalDateTime.of(expected, LocalTime.MIDNIGHT);
        assertEquals(expectedDateTime, getDateTimeString(input));
    }

    @Test
    public void testGetDateTimeStringInvalidTimeFormat() {
        String input = "09-15-2024 25:00"; // Invalid time
        assertThrows(IllegalDateFormatException.class, () -> getDateTimeString(input));
    }

    @Test
    public void testGetDateTimeStringInvalidDateFormat() {
        String input = "Invalid date";
        assertThrows(IllegalDateFormatException.class, () -> getDateTimeString(input));
    }
}
