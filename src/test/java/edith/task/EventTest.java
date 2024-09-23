package edith.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private String taskDescription = "Meeting";
    private String startTimeString = "10/10/2024 1400";
    private String endTimeString = "10/10/2024 1600";

    @Test
    public void constructor_validTimes_success() {
        Event event = new Event(taskDescription, startTimeString, endTimeString);

        assertEquals("Meeting", event.taskString());
        assertEquals(LocalDateTime.of(2024, 10, 10, 14, 0), event.getStartTime().atTime(14, 0));
        assertEquals(LocalDateTime.of(2024, 10, 10, 16, 0), event.getEndTime().atTime(16, 0));
    }

    @Test
    public void savedTaskString_validInput_success() {
        Event event = new Event(taskDescription, startTimeString, endTimeString);
        assertEquals("Meeting /from 10/10/2024 1400 /to 10/10/2024 1600", event.savedTaskString());
    }

    @Test
    public void toString_validInput_success() {
        Event event = new Event(taskDescription, startTimeString, endTimeString);
        String expectedString = "[E] [ ] Meeting (from: Thu, 10 Oct 2024, 2PM - to: Thu, 10 Oct 2024, 4PM)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void getStartTime_validInput_success() {
        Event event = new Event(taskDescription, startTimeString, endTimeString);
        assertEquals(LocalDate.of(2024, 10, 10), event.getStartTime());
    }

    @Test
    public void getEndTime_validInput_success() {
        Event event = new Event(taskDescription, startTimeString, endTimeString);
        assertEquals(LocalDate.of(2024, 10, 10), event.getEndTime());
    }
}
