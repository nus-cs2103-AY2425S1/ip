package shoai;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventTest {

    @Test
    public void testEventConstructor() {
        // Use LocalDateTime for the from and to parameters
        LocalDateTime from = LocalDateTime.parse("2024-08-30T09:00:00");
        LocalDateTime to = LocalDateTime.parse("2024-08-31T17:00:00");

        Event event = new Event("Project meeting", from, to);

        assertNotNull(event);
        assertEquals("Project meeting", event.getDescription());
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }

    @Test
    public void testToString() {
        // Use LocalDateTime for the from and to parameters
        LocalDateTime from = LocalDateTime.parse("2024-08-30T09:00:00");
        LocalDateTime to = LocalDateTime.parse("2024-08-31T17:00:00");

        Event event = new Event("Project meeting", from, to);

        // Use DateTimeFormatter to format dates similarly to the toString method
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String expectedString = "[E][ ] Project meeting (from: "
                + from.format(formatter)
                + " to: "
                + to.format(formatter)
                + ")";
        assertEquals(expectedString, event.toString());
    }
}
