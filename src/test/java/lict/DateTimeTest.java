package lict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void testDateParsing() {
        DateTime dateTime = new DateTime("2024-09-30");
        assertEquals("Sep 30 2024", dateTime.getString());
        assertEquals("2024-09-30", dateTime.getData());
    }

    @Test
    public void testDateTimeParsing() {
        DateTime dateTime1 = new DateTime("2024-09-30 2359");
        assertEquals("Sep 30 2024 11:59pm", dateTime1.getString());
        assertEquals("2024-09-30 2359", dateTime1.getData());

        DateTime dateTime2 = new DateTime("2024-09-30 0815");
        assertEquals("Sep 30 2024 8:15am", dateTime2.getString());
        assertEquals("2024-09-30 0815", dateTime2.getData());
    }

    @Test
    public void testInvalidInput() {
        try {
            new DateTime("RandomInput1@3");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text 'RandomInput1@3' could not be parsed at index 0", e.getMessage());
        }

        try {
            new DateTime("2024-09-54 2612");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text '2024-09-54 2612' could not be parsed: "
                    + "Invalid value for DayOfMonth (valid values 1 - 28/31): 54", e.getMessage());
        }
    }


    @Test
    public void testInvalidDateFormat() {
        try {
            new DateTime("06/01/2023");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text '06/01/2023' could not be parsed at index 0", e.getMessage());
        }

        try {
            new DateTime("Sep 30 2024");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text 'Sep 30 2024' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void testInvalidDateTimeFormat() {
        try {
            new DateTime("06/01/2023 1800");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text '06/01/2023 1800' could not be parsed at index 0", e.getMessage());
        }

        try {
            new DateTime("2023-01-05 06:12PM");
            fail("DateTimeException was not thrown");
        } catch (DateTimeException e) {
            assertEquals("Text '2023-01-05 06:12PM' could not be parsed at index 13", e.getMessage());
        }
    }
}
