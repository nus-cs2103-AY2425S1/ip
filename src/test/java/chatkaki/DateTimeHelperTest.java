package chatkaki;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DateTimeHelperTest {
    @Test
    public void parseDate_validDate_success() {
        String dateStr = "2/12/2019 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2019, 12, 2, 18, 0);
        assertEquals(expectedDate, DateTimeHelper.parseDate(dateStr));
    }

    @Test
    public void parseDate_invalidDate_returnsNull() {
        String dateStr = "invalid date";
        assertNull(DateTimeHelper.parseDate(dateStr));
    }

    @Test
    public void isValidDateFormat_validDate_returnsTrue() {
        String dateStr = "2/12/2019 1800";
        assertTrue(DateTimeHelper.isValidDateFormat(dateStr));
    }

    @Test
    public void isValidDateFormat_invalidDate_returnsFalse() {
        String dateStr = "invalid date";
        assertFalse(DateTimeHelper.isValidDateFormat(dateStr));
    }
}