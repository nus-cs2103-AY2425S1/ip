package torne.util;

import org.junit.jupiter.api.BeforeEach;
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

    @Test
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
    void testToRelativeString_oneDayPast_yesterday() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        TorneDateTime pastTorneDateTime = new TorneDateTime(pastDate);
        String relativeString = pastTorneDateTime.toRelativeString();
        assertEquals("yesterday", relativeString, "Relative string should be 'yesterday' for a date one day in the past.");
    }

    @Test
    void testToRelativeString_oneDayFuture_tomorrow() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        TorneDateTime futureTorneDateTime = new TorneDateTime(futureDate);
        String relativeString = futureTorneDateTime.toRelativeString();
        assertEquals("tomorrow", relativeString, "Relative string should be 'tomorrow' for a date one day in the future.");
    }

    @Test
    void testToRelativeString_currentTime_justNow() {
        LocalDateTime nowDate = LocalDateTime.now();
        TorneDateTime nowTorneDateTime = new TorneDateTime(nowDate);
        String relativeString = nowTorneDateTime.toRelativeString();
        assertEquals("just now", relativeString, "Relative string should be 'just now' for a date matching the current time.");
    }
}
