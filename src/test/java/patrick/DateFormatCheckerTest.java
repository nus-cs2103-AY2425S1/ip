package patrick;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
