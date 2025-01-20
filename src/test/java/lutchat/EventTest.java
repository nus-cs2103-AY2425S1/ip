package lutchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

public class EventTest {

    @Test
    public void testEventCreationAndToString() {
        String description = "Test Event";
        String fromDate = "2024-08-01";
        String toDate = "2024-08-10";
        Event event = new Event(description, fromDate, toDate);

        String expectedToString = "[E][ ] Test Event (from: Aug 01 2024 to: Aug 10 2024)";

        String actualToString = event.toString();

        assertEquals(expectedToString, actualToString, "Event's string representation should match the expected format.");
    }

    @Test
    public void testEventCreationWithInvalidInput() {
        String description = "Invalid Event";
        String invalidFromDate = "hello world";
        String validToDate = "2024-08-10";

        assertThrows(IllegalArgumentException.class, () -> new Event(description, invalidFromDate, validToDate),
                "Creating an event with an invalid date should throw an exception.");
    }

    @Test
    public void testEventCreationWithWrongDateFormat() {
        String description = "Invalid Event";
        String invalidFromDate = "2024/08/10";
        String validToDate = "2024-08-10";

        assertThrows(IllegalArgumentException.class, () -> new Event(description, invalidFromDate, validToDate),
                "Creating an event with an invalid date should throw an exception.");
    }
}