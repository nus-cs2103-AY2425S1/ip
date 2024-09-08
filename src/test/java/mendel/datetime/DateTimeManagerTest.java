package mendel.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTimeManagerTest {
    @Test
    public void detectDate() {
        assertEquals("Jan 01 2024", new DateTimeManager("01/Jan/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01/01/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1/1/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1/Jan/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01-Jan-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01-01-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1-1-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1-Jan-2024").toString());
    }

    @Test
    public void detectDateTime() {
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/Jan/2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/01/2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/1/2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/Jan/2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-Jan-2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-01-2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-1-2024 1500").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-Jan-2024 1500").toString());

        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/Jan/2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/01/2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/1/2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/Jan/2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-Jan-2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-01-2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-1-2024 15 00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-Jan-2024 15 00").toString());

        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/Jan/2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01/01/2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/1/2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1/Jan/2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-Jan-2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("01-01-2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-1-2024 15:00").toString());
        assertEquals("Jan 01 2024, 15:00", new DateTimeManager("1-Jan-2024 15:00").toString());
    }

    @Test
    public void removeTimeStamp() {
        assertEquals("Jan 01 2024", new DateTimeManager("01/Jan/2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01/01/2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/1/2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/Jan/2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-Jan-2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-01-2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-1-2024 1500").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-Jan-2024 1500").removeTimeStamp());

        assertEquals("Jan 01 2024", new DateTimeManager("01/Jan/2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01/01/2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/1/2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/Jan/2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-Jan-2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-01-2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-1-2024 15 00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-Jan-2024 15 00").removeTimeStamp());

        assertEquals("Jan 01 2024", new DateTimeManager("01/Jan/2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01/01/2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/1/2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1/Jan/2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-Jan-2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("01-01-2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-1-2024 15:00").removeTimeStamp());
        assertEquals("Jan 01 2024", new DateTimeManager("1-Jan-2024 15:00").removeTimeStamp());
    }
}
