package cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cookie.task.Event;

public class EventTest {
    @Test
    void testConstructor() {
        String description = "Meeting";
        String from = "2024-08-25";
        String to = "2024-08-26";

        Event event = new Event(description, from, to);
        assertEquals(description, event.getDescription());
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }
    @Test
    void testToString() {
        LocalDate fromDate = LocalDate.of(2024, 8, 25);
        LocalDate toDate = LocalDate.of(2024, 8, 26);
        String from = "2024-08-25";
        String to = "2024-08-26";

        DateParserStub.convertStringToDate(from);
        DateParserStub.convertStringToDate(to);
        DateParserStub.changePattern(fromDate);
        DateParserStub.changePattern(toDate);

        Event event = new Event("Workshop", from, to);
        assertEquals("[E][] Workshop (from: Aug 25 2024 to: Aug 26 2024 )", event.toString());

        event.markDone();
        assertEquals("[E][X] Workshop (from: Aug 25 2024 to: Aug 26 2024 )", event.toString());
    }

    static class DateParserStub {
        public static LocalDate convertStringToDate(String date) {
            return LocalDate.parse(date);
        }
        public static String changePattern(LocalDate date) {
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }
}
