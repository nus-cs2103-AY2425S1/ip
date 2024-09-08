package infinity.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void prependZero_singleDigit_zeroPrepended() {
        assertEquals(Parser.prependZero(1), "01");
        System.out.println("Test 1: 1 => '01' - passed");
    }

    @Test
    public void prependZero_doubleDigit_zeroNotPrepended() {
        assertEquals(Parser.prependZero(10), "10");
        System.out.println("Test 2: 10 => '10' - passed");
    }

    @Test
    public void parseDateTime_validDateGiven_objectLocalDateTimeReturned() {
        assertEquals(Parser.parseDateTime("01/12/2024 0059"),
                LocalDateTime.of(2024, 12, 1, 0, 59));
        assertEquals(Parser.parseDateTime("01-12-2024 0059"),
                LocalDateTime.of(2024, 12, 1, 0, 59));
        assertEquals(Parser.parseDateTime("1-1-2024 0000"),
                LocalDateTime.of(2024, 1, 1, 0, 0));
        System.out.println("Test 3: Valid Date - passed");
    }

    @Test
    public void parseDateTime_invalidDateGiven_exceptionDateTimeExceptionThrown() {
        try {
            assertEquals(Parser.parseDateTime("01/12/2024 2459"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        try {
            assertEquals(Parser.parseDateTime("01/12/2024 2360"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        try {
            assertEquals(Parser.parseDateTime("32/12/2024 2459"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        try {
            assertEquals(Parser.parseDateTime("00/12/2024 2459"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        try {
            assertEquals(Parser.parseDateTime("01/13/2024 2459"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        try {
            assertEquals(Parser.parseDateTime("01/00/2024 2459"),
                    LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
        System.out.println("Test 4: Invalid Dates - passed");
    }
}
