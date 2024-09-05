import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import nathanbot.tasks.Event;

public class EventTest {

    @Test
    public void testToString() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 5, 14, 30);
        LocalDateTime endTime = LocalDateTime.of(2024, 10, 5, 14, 30);
        Event event = new Event("Submit assignment", startTime, endTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        String expected = "[E][ ] Submit assignment (from: " + startTime.format(dateTimeFormatter) + " to: "
            + endTime.format(dateTimeFormatter) + ")";
        assertEquals(expected, event.toString());
    }
}
