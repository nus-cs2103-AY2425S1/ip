package loafy.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseDateTime_correctInput_success() throws Exception {
        // normal input results in correct LocalDateTime
        String input = "25/12/2024 2359";
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        assertEquals(dateTime, Parser.parseDateTime(input));

        // single digits for date and month results in correct LocalDateTime;
        input = "2/1/2024 2359";
        dateTime = LocalDateTime.of(2024, 1, 2, 23, 59);
        assertEquals(dateTime, Parser.parseDateTime(input));

        // padded date and month results in correct LocalDateTime;
        input = "02/01/2024 2359";
        dateTime = LocalDateTime.of(2024, 1, 2, 23, 59);
        assertEquals(dateTime, Parser.parseDateTime(input));

        // padded time and hour results in correct LocalDateTime;
        input = "25/12/2024 0309";
        dateTime = LocalDateTime.of(2024, 12, 25, 3, 9);
        assertEquals(dateTime, Parser.parseDateTime(input));

        //day of week results in correct LocalDateTime, todo update date again when testing
        input = "Monday";
        dateTime = LocalDateTime.of(2024, 9, 23, 23, 59);
        assertEquals(dateTime, Parser.parseDateTime(input));

        //day of week and time results in correct LocalDateTime, todo update date again when testing
        input = "tuesday 1200";
        dateTime = LocalDateTime.of(2024, 9, 24, 12, 00);
        assertEquals(dateTime, Parser.parseDateTime(input));
    }

    @Test
    public void parseDateTime_nonPaddedHour_exceptionThrown() {
        try {
            String input = "25/12/2024 359";
            LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 3, 59);
            assertEquals(dateTime, Parser.parseDateTime(input));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            String wrongDateFormatMsg = "Date format should be written as D/M/YYYY or day of week.\n" +
                    "Time in 24HR clock format is optional.\n" +
                    "eg. 1/9/2024 2359\n" +
                    "eg. wednesday";
            assertEquals(wrongDateFormatMsg, e.getMessage());
        }
    }

    @Test
    public void parseDateTime_truncatedYear_exceptionThrown() {
        try {
            String input = "25/12/24 2359";
            LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
            assertEquals(dateTime, Parser.parseDateTime(input));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            String wrongDateFormatMsg = "Date format should be written as D/M/YYYY or day of week.\n" +
                    "Time in 24HR clock format is optional.\n" +
                    "eg. 1/9/2024 2359\n" +
                    "eg. wednesday";
            assertEquals(wrongDateFormatMsg, e.getMessage());
        }
    }

    @Test
    public void parseDateTime_truncatedDay_exceptionThrown() {
        try {
            String input = "Mon";
            LocalDateTime dateTime = LocalDateTime.of(2024, 9, 23, 23, 59);
            assertEquals(dateTime, Parser.parseDateTime(input));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            String wrongDateFormatMsg = "Date format should be written as D/M/YYYY or day of week.\n" +
                    "Time in 24HR clock format is optional.\n" +
                    "eg. 1/9/2024 2359\n" +
                    "eg. wednesday";
            assertEquals(wrongDateFormatMsg, e.getMessage());
        }
    }
}
