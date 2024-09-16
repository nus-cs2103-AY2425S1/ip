package thanos.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidDateException;

public class DateTimeUtilityTest {
    @Test
    public void testParseWithValidDateTimeFormats() throws InvalidDateException {
        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26/8/2024 1430"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26/8/2024 14:30"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26-08-2024 1430"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26-08-2024 14:30"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26 Aug 2024 1430"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("26 Aug 2024 14:30"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("Aug 26 2024 1430"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("Aug 26 2024 14:30"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("2024-08-26 1430"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 14, 30),
                DateTimeUtility.parse("2024-08-26 14:30"));
    }

    @Test
    public void testParseWithValidDateFormats() throws InvalidDateException {
        assertEquals(LocalDateTime.of(2024, 8, 26, 0, 0),
                DateTimeUtility.parse("26/8/2024"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 0, 0),
                DateTimeUtility.parse("26-08-2024"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 0, 0),
                DateTimeUtility.parse("26 Aug 2024"));

        assertEquals(LocalDateTime.of(2024, 8, 26, 0, 0),
                DateTimeUtility.parse("2024-08-26"));
    }

    @Test
    public void testParseWithInvalidDateTimeFormat() {
        assertThrows(InvalidDateException.class, () -> DateTimeUtility.parse("Tomorrow 2 PM"),
                "Expected InvalidDateException to be thrown");
    }

    @Test
    public void testFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 14, 30);
        assertEquals("Aug 26 2024 14:30", DateTimeUtility.format(dateTime));
    }
}
