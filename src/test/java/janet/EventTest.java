package janet;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventCreation_success() throws Exception {
        assertEquals(
                new Event("project meeting",
                        "E",
                        LocalDateTime.of(2024, 8, 30, 18, 0),
                        LocalDateTime.of(2024, 9, 30, 18, 0)
                ),
                new Event("event project meeting /from 2024-08-30 18:00 /to 2024-09-30 18:00")
        );

        assertEquals(
                new Event("event project meeting /from 2024-08-30 18:00 /to 2024-09-30 18:00").getStartDate(),
                LocalDateTime.of(2024, 8, 30, 18, 0).format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")
                )
        );

        assertEquals(
                new Event("event project meeting /from 2024-08-30 18:00 /to 2024-09-30 18:00").getEndDate(),
                LocalDateTime.of(2024, 9, 30, 18, 0).format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")
                )
        );
    }
}
