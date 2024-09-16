package moimoi.util.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import moimoi.util.exception.InvalidDateTimeRangeException;

public class EventTest {

    private String description = "dummy";
    private LocalDateTime datetimeEarly = LocalDateTime.parse("2024-08-24 12:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private LocalDateTime datetimeLate = LocalDateTime.parse("2024-08-27 17:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Test
    public void event_invalidDateRange_invalidDateTimeRangeExceptionThrown() {
        try {
            Event event = new Event(this.description, this.datetimeLate, this.datetimeEarly);
            fail();
        } catch (InvalidDateTimeRangeException e) {
            assertEquals(new InvalidDateTimeRangeException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void testMarkUnmark() throws Exception {

        Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
        assertEquals(" ", event.getStatusIcon());

        event.mark();
        assertEquals("X", event.getStatusIcon());

        event.mark();
        assertEquals("X", event.getStatusIcon());

        event.unmark();
        assertEquals(" ", event.getStatusIcon());

        event.unmark();
        assertEquals(" ", event.getStatusIcon());

    }

    @Test
    public void occurringOn_occurringDate_returnsTrue() throws Exception {

        Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);

        assertTrue(event.occursOn(LocalDate.parse("2024-08-24")));
        assertTrue(event.occursOn(LocalDate.parse("2024-08-25")));
        assertTrue(event.occursOn(LocalDate.parse("2024-08-27")));

    }

    @Test
    public void occurringOn_notOccurringDate_returnsFalse() throws Exception {
        Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
        assertFalse(event.occursOn(LocalDate.parse("2024-07-24")));
        assertFalse(event.occursOn(LocalDate.parse("2024-09-25")));
    }

    @Test
    public void hasKeyword() throws Exception {

        Event eventWithoutWhiteSpace = new Event(this.description, this.datetimeEarly, this.datetimeLate);
        Event eventWithWhiteSpace = new Event(this.description + " " + this.description,
                this.datetimeEarly, this.datetimeLate);

        assertTrue(eventWithoutWhiteSpace.hasKeyword("dummy"));
        assertTrue(eventWithoutWhiteSpace.hasKeyword("Dum"));
        assertTrue(eventWithWhiteSpace.hasKeyword(" "));

        assertFalse(eventWithoutWhiteSpace.hasKeyword("dummies"));
        assertFalse(eventWithoutWhiteSpace.hasKeyword("?"));
        assertFalse(eventWithoutWhiteSpace.hasKeyword("dummy "));
        assertFalse(eventWithoutWhiteSpace.hasKeyword(" "));

    }

    @Test
    public void testStringUI() throws Exception {
        Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
        event.mark();
        assertEquals("[E][X] " + this.description + " (from: "
                + this.datetimeEarly.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " to: "
                + this.datetimeLate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")",
                event.stringUI());
    }

    @Test
    public void testStringStorage() throws Exception {
        Event event = new Event(this.description, this.datetimeEarly, this.datetimeEarly);
        assertEquals("E |   | " + this.description + " | "
                + this.datetimeEarly.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.datetimeEarly.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                event.stringStorage());
    }

}
