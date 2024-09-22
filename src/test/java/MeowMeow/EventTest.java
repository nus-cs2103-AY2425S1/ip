package meowmeow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {
    @Test
    public void convertToFileFormat_success() throws Exception {
        assertEquals("E | 0 | trip | 2024-10-12 | 2024-10-28", new Event("trip", "2024-10-12", "2024-10-28").convertToFileFormat());
    }

    @Test
    public void convertToFileFormat_wrongDateFormat_exceptionThrown() {
        try {
            assertEquals("E | 0 | trip | 2024/10/12 | 2024/10/28", new Event("trip", "2024/10/12", "2024/10/28").convertToFileFormat());
            fail();
        } catch (Exception e) {
            assertEquals("Text '2024/10/12' could not be parsed at index 4", e.getMessage());
        }
    }

    @Test
    void testEventCreation() {
        Event event = new Event("Project meeting", "2023-09-01", "2023-09-02");
        assertEquals("[E][ ] Project meeting (from: Sept 01 2023 to: Sept 02 2023)", event.toString(),
                "The string representation of the Event is incorrect.");
    }

    @Test
    void testEventMarkedAsDone() {
        Event event = new Event("Project meeting", "2023-09-01", "2023-09-02");
        event.markDone();
        assertEquals("[E][X] Project meeting (from: Sept 01 2023 to: Sept 02 2023)", event.toString(),
                "The string representation when the event is marked as done is incorrect.");
    }

}
