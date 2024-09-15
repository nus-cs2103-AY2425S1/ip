package patrick;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateFormatCheckerTest {

    @Test
    public void testIsValidDate_validDate() {
        boolean result = DateFormatChecker.isValidDate("2023-09-15 1200");
        assertTrue(result);
    }

    @Test
    public void testIsValidDate_invalidDate() {
        boolean result = DateFormatChecker.isValidDate("2023-99-99");
        assertFalse(result);
    }

    @Test
    public void testIsValidDate_emptyString() {
        boolean result = DateFormatChecker.isValidDate("");
        assertFalse(result);
    }
}
