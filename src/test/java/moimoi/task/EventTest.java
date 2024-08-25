package moimoi.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import moimoi.exception.InvalidDateTimeRangeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    String description = "dummy";
    LocalDateTime datetimeEarly = LocalDateTime.parse("2024-08-24 12:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    LocalDateTime datetimeLate = LocalDateTime.parse("2024-08-27 17:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Test
    public void Event_invalidDateRange_invalidDateTimeRangeExceptionThrown() {
        try {
            Event event = new Event(this.description, this.datetimeLate, this.datetimeEarly);
            fail();
        } catch (InvalidDateTimeRangeException e) {
            assertEquals(new InvalidDateTimeRangeException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void testMarkUnmark() {
        try {
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
        } catch (InvalidDateTimeRangeException e) {
            fail();
        }
    }

    @Test
    public void occurringOn_occurringDate_returnsTrue() {
        try {
            Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
            assertTrue(event.occurringOn(LocalDate.parse("2024-08-24")));
            assertTrue(event.occurringOn(LocalDate.parse("2024-08-25")));
            assertTrue(event.occurringOn(LocalDate.parse("2024-08-27")));
        } catch (InvalidDateTimeRangeException e) {
            fail();
        }
    }

    @Test
    public void occurringOn_notOccurringDate_returnsFalse() {
        try {
            Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
            assertFalse(event.occurringOn(LocalDate.parse("2024-07-24")));
            assertFalse(event.occurringOn(LocalDate.parse("2024-09-25")));
        } catch (InvalidDateTimeRangeException e) {
            fail();
        }
    }

    @Test
    public void testStringUI() {
        try {
            Event event = new Event(this.description, this.datetimeEarly, this.datetimeLate);
            event.mark();
            assertEquals("[E][X] " + this.description + " (from: "
                    + this.datetimeEarly.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " to: "
                    + this.datetimeLate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")",
                    event.stringUI());
        } catch (InvalidDateTimeRangeException e) {
            fail();
        }
    }

    @Test
    public void testStringStorage() {
        try {
            Event event = new Event(this.description, this.datetimeEarly, this.datetimeEarly);
            assertEquals("E |   | " + this.description + " | "
                    + this.datetimeEarly.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                    + this.datetimeEarly.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    event.stringStorage());
        } catch (InvalidDateTimeRangeException e) {
            fail();
        }
    }

}
