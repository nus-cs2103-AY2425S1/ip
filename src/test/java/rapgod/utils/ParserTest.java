package rapgod.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseToDateTime_validFormat1_dateTimeObjectReturned() {
        LocalDateTime actual = Parser.parseToDateTime("23/08/2024 1600");
        LocalDateTime expected = LocalDateTime.of(2024, 8, 23, 16, 0, 0);

        assertEquals(actual, expected);

    }


    @Test
    public void testParseToDateTime_validFormat2_dateTimeObjectReturned() {
        LocalDateTime actual = Parser.parseToDateTime("Jan 01 2024");
        LocalDateTime expected = LocalDateTime.of(2024, 1, 1, 0, 0, 0);

        assertEquals(actual, expected);

    }

    @Test
    public void testParseToDateTime_validFormat3_dateTimeObjectReturned() {
        LocalDateTime actual = Parser.parseToDateTime("Jan 01 2024 4:00pm");
        LocalDateTime expected = LocalDateTime.of(2024, 1, 1, 16, 0, 0);

        assertEquals(actual, expected);

    }

    @Test
    public void testParseToDateTime_validFormat4_dateTimeObjectReturned() {
        LocalDateTime actual = Parser.parseToDateTime("Jan 01 2024 04:30pm");
        LocalDateTime expected = LocalDateTime.of(2024, 1, 1, 16, 30, 0);

        assertEquals(actual, expected);

    }

    @Test
    public void testParseToDateTime_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseToDateTime("1/1/2024 04:30pm");
        });
    }


}
