package futureyou;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import futureyou.task.Events;

/**
 * Tests for the {@link Events} class.
 */
public class EventsTest {
    @Test
    public void events_normalInput_writtenCorrectly() {
        String taskName = "Submit 2103";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
        LocalDateTime startDate = LocalDateTime.of(2024, 9, 05, 14, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 9, 20, 22, 25);
        Events event = new Events(taskName, startDate, endDate);

        assertEquals(startDate.format(format), event.getStartDate());
        assertEquals(endDate.format(format), event.getEndDate());
    }

}
