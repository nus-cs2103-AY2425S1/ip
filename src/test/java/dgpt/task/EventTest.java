package dgpt.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void toString_dateTime_success() throws DateTimeParseException {
        // normal creation of Event with proper time date formatting
        assertEquals("[E][ ] test (from: 1 Jan 1999, 6:00am to: 2 Feb 2000, 8:00pm)",
                new Event("test", "01/01/1999 0600", "02/02/2000 2000").toString());
    }

    @Test
    public void toString_dateTime_dateTimeParseExceptionThrown() {
        try {
            assertEquals("[E][ ] test (from: 1 Jan 1999, 6:00am to: 2 Feb 2000, 8:00pm)",
                    new Event("test", "01-01-1999 0600", "02-02-2000 2000").toString());
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '01-01-1999 0600' could not be parsed at index 2", e.getMessage());
        }
    }

}
