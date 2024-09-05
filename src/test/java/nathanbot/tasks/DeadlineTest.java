import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import nathanbot.tasks.Deadline;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 5, 14, 30);
        Deadline deadline = new Deadline("Submit assignment", dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        String expected = "[D][ ] Submit assignment (by: " + dateTime.format(dateTimeFormatter) + ")";
        assertEquals(expected, deadline.toString());
    }
}
