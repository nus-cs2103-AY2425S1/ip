package drbrown.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    private String description;
    private String validStartTime;
    private String validEndTime;
    private String invalidTime;
    private DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        description = "Meeting";
        validStartTime = "03-09-2024 09:00";
        validEndTime = "03-09-2024 10:00";
        invalidTime = "03-09-24 09:00";
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    @Test
    public void testSuccessfulCreationEvent() {
        Event event = new Event(false, this.description, LocalDateTime.parse(this.validStartTime, formatter), LocalDateTime.parse(this.validEndTime, formatter));
        assertEquals("[E][ ] Meeting (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }

    @Test
    public void testInvalidDateTimeEvent() {
        try {
            Event event = new Event(false, this.description, LocalDateTime.parse(this.invalidTime, formatter), LocalDateTime.parse(this.validEndTime, formatter));
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
        }
    }

    @Test
    void testToFileStringEvent() {
        Event event = new Event(false, this.description, LocalDateTime.parse(this.validStartTime, formatter), LocalDateTime.parse(this.validEndTime, formatter));
        assertEquals("E | false | Meeting | 2024-09-03 0900 | 2024-09-03 1000", event.toFileString());
    }

    @Test
    void testToUIStringEvent() {
        Event event = new Event(false, this.description, LocalDateTime.parse(this.validStartTime, formatter), LocalDateTime.parse(this.validEndTime, formatter));
        assertEquals("The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n", event.toUIString());
    }

    @Test
    public void testSuccessfulCreationMarkDoneEvent() {
        Event event = new Event(true, this.description, LocalDateTime.parse(this.validStartTime, formatter), LocalDateTime.parse(this.validEndTime, formatter));
        assertEquals("[E][X] Meeting (from: Sep 03 2024 end: Sep 03 2024 10:00)", event.toString());
    }
}
