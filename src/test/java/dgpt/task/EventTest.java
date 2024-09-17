package dgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void toString_dateTime_success() throws DateTimeParseException {
        // normal creation of Event with proper time date formatting
        assertEquals("[E][ ] test (from: 01 Jan 1999, 6:00AM to: 02 Feb 2000, 8:00PM)",
                new Event("test", "01/01/1999 0600", "02/02/2000 2000").toString());
    }

    @Test
    public void toString_dateTime_dateTimeParseExceptionThrown() {
        try {
            assertEquals("[E][ ] test (from: 01 Jan 1999, 6:00AM to: 02 Feb 2000, 8:00PM)",
                    new Event("test", "01-01-1999 0600", "02-02-2000 2000").toString());
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '01-01-1999 0600' could not be parsed at index 2", e.getMessage());
        }
    }

}
