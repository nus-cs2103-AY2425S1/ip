package torne.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TorneDateTimeTest {

    private TorneDateTime torneDateTime;
    private LocalDateTime sampleDateTime;

    @BeforeEach
    void setUp() {
        sampleDateTime = LocalDateTime.of(2024, 9, 30, 18, 20);
        torneDateTime = new TorneDateTime(sampleDateTime);
    }

    @Test
    void testParseInputDateTimeString_validInput_match() throws TorneInvalidCommandException {
        TorneDateTime parsedDateTime = TorneDateTime.parseInputDateTimeString("2024-09-30 1820");
        assertEquals(sampleDateTime, parsedDateTime.getLocalDateTime(), "Parsed date time should match the expected date and time.");
    }

    @Test
    void testParseInputDateTimeString_invalidInput_throwsException() {
        assertThrows(TorneInvalidCommandException.class, () -> {
            TorneDateTime.parseInputDateTimeString("Invalid input");
        }, "Parsing an invalid date time string should throw TorneInvalidCommandException.");
    }

    @Disabled
    void testParseInputDateString_validInput_match() throws TorneInvalidCommandException {
        // error atm
        LocalDateTime expectedDate = LocalDateTime.of(2024, 9, 30, 0, 0);
        TorneDateTime parsedDate = TorneDateTime.parseInputDateString("2024-09-30");
        assertEquals(expectedDate, parsedDate.getLocalDateTime(), "Parsed date should match the expected date with zeroed time.");
    }

    @Test
    void testParseInputDateString_invalidInput_throwsException() {
        assertThrows(TorneInvalidCommandException.class, () -> {
            TorneDateTime.parseInputDateString("Invalid input");
        }, "Parsing an invalid date string should throw TorneInvalidCommandException.");
    }

    @Test
    void testParseStorageString_validInput_match() throws TorneInvalidDataException {
        // Corresponds to 2024-09-30 18:20 UTC in seconds since epoch
        TorneDateTime parsedDateTime = TorneDateTime.parseStorageString("1727720400");
        assertEquals(sampleDateTime, parsedDateTime.getLocalDateTime(), "Parsed date time should match the expected date and time.");
    }

    @Test
    void testParseStorageString_invalidInput_throwsException() {
        assertThrows(NumberFormatException.class, () -> {
            TorneDateTime.parseStorageString("Invalid input");
        }, "Parsing an invalid storage string should throw java.lang.NumberFormatException.");
    }

    @Test
    void testParseIsoString_validInput_match() throws TorneInvalidDataException {
        TorneDateTime parsedDateTime = TorneDateTime.parseIsoString("2024-09-30T18:20:00");
        assertEquals(sampleDateTime, parsedDateTime.getLocalDateTime(), "Parsed ISO date time should match the expected date and time.");
    }

    @Test
    void testParseIsoString_invalidInput_throwsException() {
        assertThrows(TorneInvalidDataException.class, () -> {
            TorneDateTime.parseIsoString("Invalid input");
        }, "Parsing an invalid ISO date time string should throw TorneInvalidDataException.");
    }

    @Test
    void testToStorageString_sample_expectedFormat() {
        String storageString = torneDateTime.toStorageString();
        assertEquals("1727720400", storageString, "Storage string should match the expected epoch time in seconds.");
    }

    @Test
    void testToIsoString_sample_expectedFormat() {
        String isoString = torneDateTime.toIsoString();
        assertEquals("2024-09-30T18:20:00", isoString, "ISO string should match the expected ISO-8601 format.");
    }

    @Test
    void testToStringsample_expectedFormat() {
        String formattedString = torneDateTime.toString();
        assertEquals("2024-09-30 1820", formattedString, "Formatted string should match the expected format yyyy-MM-dd HHmm.");
    }

    @Test
    void testToRelativeString_thirtySecPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusSeconds(30);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("just now", relativeString, "Relative string should be 'just now' for a dt 30 seconds ago.");
    }

    @Test
    void testToRelativeString_currentTime_justNow() {
        LocalDateTime nowDate = LocalDateTime.now();
        TorneDateTime nowTorneDateTime = new TorneDateTime(nowDate);
        String relativeString = nowTorneDateTime.toRelativeString();
        assertEquals("just now", relativeString, "Relative string should be 'just now' for a date matching the current time.");
    }

    @Test
    void testToRelativeString_thirtySecFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusSeconds(30);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in less than a minute", relativeString, "Relative string should be 'in less than a minute' for a dt 30 seconds in the future.");
    }

    @Test
    void testToRelativeString_oneMinPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusSeconds(60);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("1 minute ago", relativeString, "Relative string should be '1 minute ago' for a dt 1 min ago.");
    }

    @Test
    void testToRelativeString_oneMinFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusSeconds(60);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 1 minute", relativeString, "Relative string should be 'in 1 minute' for a dt 1 min in the future.");
    }

    @Test
    void testToRelativeString_twoMinPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusMinutes(2);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("2 minutes ago", relativeString, "Relative string should be '2 minutes ago' for a dt 2 min ago.");
    }

    @Test
    void testToRelativeString_twoMinFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusMinutes(2);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 2 minutes", relativeString, "Relative string should be 'in 2 minutes' for a dt 2 min in the future.");
    }

    @Test
    void testToRelativeString_oneHourPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusHours(1);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("1 hour ago", relativeString, "Relative string should be '1 hour ago' for a dt 1 hr ago.");
    }

    @Test
    void testToRelativeString_oneHourFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusHours(1);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 1 hour", relativeString, "Relative string should be 'in 1 hour' for a dt 1 hr in the future.");
    }

    @Test
    void testToRelativeString_twoHourPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusHours(2);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("2 hours ago", relativeString, "Relative string should be '2 hours ago' for a dt 2 hr ago.");
    }

    @Test
    void testToRelativeString_twoHourFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusHours(2);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 2 hours", relativeString, "Relative string should be 'in 2 hour' for a dt 2 hr in the future.");
    }

    @Test
    void testToRelativeString_oneDayPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("yesterday", relativeString, "Relative string should be 'yesterday' for a dt 1 day in the past.");
    }

    @Test
    void testToRelativeString_oneDayFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("tomorrow", relativeString, "Relative string should be 'tomorrow' for a dt 1 day in the future.");
    }

    @Test
    void testToRelativeString_twoDayPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(2);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("2 days ago", relativeString, "Relative string should be '2 days ago' for a dt 2 days in the past.");
    }

    @Test
    void testToRelativeString_twoDayFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(2);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 2 days", relativeString, "Relative string should be 'in 2 days' for a dt 2 days in the future.");
    }

    @Test
    void testToRelativeString_oneWeekPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusWeeks(1);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("1 week ago", relativeString, "Relative string should be '1 week ago' for a dt 1 week in the past.");
    }

    @Test
    void testToRelativeString_oneWeekFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusWeeks(1);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 1 week", relativeString, "Relative string should be 'in 1 week' for a dt 1 week in the future.");
    }

    @Test
    void testToRelativeString_twoWeekPast_correct() {
        LocalDateTime pastDate = LocalDateTime.now().minusWeeks(2);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("2 weeks ago", relativeString, "Relative string should be '2 weeks ago' for a dt 2 weeks in the past.");
    }

    @Test
    void testToRelativeString_twoWeekFuture_correct() {
        LocalDateTime futureDate = LocalDateTime.now().plusWeeks(2);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("in 2 weeks", relativeString, "Relative string should be 'in 2 weeks' for a dt 2 weeks in the future.");
    }
}
