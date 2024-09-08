package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class DateTimeFormatEnumTest {

    @Test
    public void parse_validDateTimeWithTime_correctLocalDateTime() {
        String input = "2/12/2019 1800";
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 18, 0);

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isPresent(), "Expected a valid LocalDateTime object.");
        assertEquals(expected, result.get(), "Parsed LocalDateTime should match the expected value.");
    }

    @Test
    public void parse_validDateTimeForSaving_correctLocalDateTime() {
        String input = "Dec 2 2019, 6:00 pm";
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 18, 0);

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isPresent(), "Expected a valid LocalDateTime object.");
        assertEquals(expected, result.get(), "Parsed LocalDateTime should match the expected value.");
    }

    @Test
    public void parse_validDateOnly_midnightLocalDateTime() {
        String input = "2/12/2019";
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 0, 0);

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isPresent(), "Expected a valid LocalDateTime object.");
        assertEquals(expected, result.get(), "Parsed LocalDateTime should be at midnight.");
    }

    @Test
    public void parse_invalidDateTime_emptyOptional() {
        String input = "invalid date";

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isEmpty(), "Expected parsing to fail, resulting in an empty Optional.");
    }

    @Test
    public void parse_partialDateTime_emptyOptional() {
        String input = "12/2019 1800"; // Missing day part

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isEmpty(), "Expected parsing to fail for an incomplete date-time format.");
    }

    @Test
    public void parse_differentDateFormat_emptyOptional() {
        String input = "2019-12-02 18:00"; // ISO format not supported

        Optional<LocalDateTime> result = DateTimeFormatEnum.parse(input);

        assertTrue(result.isEmpty(), "Expected parsing to fail for a format not defined in the enum.");
    }
}
