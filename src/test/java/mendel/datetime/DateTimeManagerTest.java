package mendel.datetime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeManagerTest {
    @Test
    public void detectDate(){
        assertEquals("Jan 01 2024", new DateTimeManager("01/Jan/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01/01/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1/1/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1/Jan/2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01-Jan-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("01-01-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1-1-2024").toString());
        assertEquals("Jan 01 2024", new DateTimeManager("1-Jan-2024").toString());
    }
}
