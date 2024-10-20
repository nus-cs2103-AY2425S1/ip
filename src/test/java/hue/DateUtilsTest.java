package hue;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    public void parseDateTime_validDateTimeFormat_success() {
        String dateTimeString = "28/8/2024 1530";

        LocalDateTime result = DateUtils.parseDateTime(dateTimeString);

        assertNotNull(result, "Result shouldn't null for a valid date-time string.");
        assertEquals(LocalDateTime.of(2024, 8, 28, 15, 30), result,
                "Parsed LocalDateTime should match the expected date and time.");
    }

    @Test
    public void parseDateTime_validDateFormat_success() {
        String dateString = "2024-08-28";

        LocalDateTime result = DateUtils.parseDateTime(dateString);

        assertNotNull(result, "Result shouldn't be null for a valid date string.");
        assertEquals(LocalDateTime.of(2024, 8, 28, 0, 0), result,
                "Parsed LocalDateTime should match the expected date with time set to the start of the day.");
    }

    @Test
    public void parseDateTime_invalidDateFormat_throwsException() {
        String invalidDateString = "invalid date format";

        assertThrows(DateTimeParseException.class, () -> {
            DateUtils.parseDateTime(invalidDateString);
        }, "DateTimeParseException should be thrown for a clearly invalid date string.");
    }
}