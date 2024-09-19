package pixel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class contains unit tests for the DateTimeParser class.
 */
public class DateTimeParserTest {
    /**
     * Tests the toString() method with the format "dd-MM-yyyy". It checks if the
     * parsed date is correctly converted to the expected string format.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toString_ddMMyyyy() throws Exception {
        DateTimeParser parser = new DateTimeParser("05-05-2022");
        assertEquals("05 May 2022", parser.toString());
    }

    /**
     * Tests the toString() method with the format "yyyy-MM-dd". It checks if the
     * parsed date is correctly converted to the expected string format.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toString_yyyyMMdd() throws Exception {
        DateTimeParser parser = new DateTimeParser("2022-05-05");
        assertEquals("05 May 2022", parser.toString());
    }

    /**
     * Tests the toString() method with the format "MM/dd/yyyy". It checks if the
     * parsed date is correctly converted to the expected string format.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toString_mmddyyyy() throws Exception {
        DateTimeParser parser = new DateTimeParser("05/05/2022");
        assertEquals("05 May 2022", parser.toString());
    }

    /**
     * Tests the toString() method with an invalid date format. It checks if a
     * PixelException is thrown when an invalid date is provided.
     */
    @Test
    public void toString_invalidDate() {
        assertThrows(PixelException.class, () -> {
            new DateTimeParser("invalid-date");
        });
    }

    /**
     * Tests the toString() method with an empty string. It checks if a
     * PixelException is thrown when an empty string is provided.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toString_emptyString() throws Exception {
        assertThrows(PixelException.class, () -> {
            new DateTimeParser("");
        });
    }
}
