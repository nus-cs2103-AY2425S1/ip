package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void getType_success() {
        // getType() should return "E" for EventTask
        assertEquals("E", new EventTask("party", LocalDateTime.of(2024, 9, 15, 16, 30), "19:30").getType());
    }

    @Test
    public void getDate_success() {
        // getDate() should return LocalDate object
        assertEquals(LocalDate.of(2024, 9, 15), new EventTask("party",
                LocalDateTime.of(2024, 9, 15, 16, 30), "19:30").getDate());
    }

    @Test
    public void getFrom_success() {
        // getFrom() should return String object
        assertEquals(LocalDateTime.of(2024, 9, 15, 16, 30).toString(),
                new EventTask("party", LocalDateTime.of(2024, 9, 15, 16, 30), "19:30").getFrom());
    }

    @Test
    public void getTo_success() {
        // getTo() should return String object
        assertEquals(LocalDateTime.of(2024, 9, 15, 19, 30).toString(),
                new EventTask("party", LocalDateTime.of(2024, 9, 15, 16, 30), "19:30").getTo());
    }

    @Test
    public void toString_sameDate_success() {
        // toString() should return
        // "[E][ ] party (from: 15 September 2024 4.30pm to: 15 September 2024 7.30pm)" for EventTask
        assertEquals("[E][ ] party (from: 15 September 2024 4:30pm to: 15 September 2024 7:30pm)",
                new EventTask("party", LocalDateTime.of(2024, 9, 15, 16, 30), "19:30").toString());
    }

    @Test
    public void toString_differentDate_success() {
        // toString() should return
        // "[E][ ] sleepover (from: 15 September 2024 4.30pm to: 16 September 2024 9.30am)" for EventTask
        assertEquals("[E][ ] sleepover (from: 15 September 2024 4:30pm to: 16 September 2024 9:30am)",
                new EventTask("sleepover", LocalDateTime.of(2024, 9, 15, 16, 30),
                        LocalDateTime.of(2024, 9, 16, 9, 30)).toString());
    }
}
