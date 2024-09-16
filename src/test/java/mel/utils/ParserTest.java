package mel.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import mel.exceptions.ParseException;

public class ParserTest {
    @Test
    public void parseDateTime_invalidDate_exceptionThrown() {
        try {
            assertEquals(LocalDate.parse("2022-02-02").atStartOfDay(),
                    new Parser().parseDateTime("30-2-2022"));
            fail();
        } catch (ParseException e) {
            assertEquals("Incorrect date/time: 30-2-2022", e.toString());
        } catch (Exception e) {
            fail("Unexpected exception caught " + e.getMessage());
        }
    }

    @Test
    public void parseDateTime_invalidFormat_exceptionThrown() {
        try {
            assertEquals(LocalDate.parse("2022-02-02").atStartOfDay(),
                    new Parser().parseDateTime("2.2.22 0000"));
            fail();
        } catch (ParseException e) {
            assertEquals("Incorrect date/time: 2.2.22 0000", e.toString());
        } catch (Exception e) {
            fail("Unexpected exception caught " + e.getMessage());
        }
    }

    @Test
    public void parseDateTime_validFormat_success() {
        try {
            assertEquals(LocalDate.parse("2022-02-02").atStartOfDay(),
                    new Parser().parseDateTime("2/2/22"));
        } catch (Exception e) {
            fail("Unexpected exception caught: " + e.getMessage());
        }
    }
}
