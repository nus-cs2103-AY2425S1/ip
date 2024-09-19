package tayoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_anyString_returnsCorrectString() {
        String EventName = "Return Book";
        String start = "today";
        String end = "tomorrow";
        Event testEvent1 = new Event(EventName, start, end, false);
        String expected = "[E][ ] Return Book (from: today to: tomorrow)";
        String actual = testEvent1.toString();
        assertEquals(expected, actual);

        Event testEvent2 = new Event(EventName, start, end, true);
        expected = "[E][X] Return Book (from: today to: tomorrow)";
        actual = testEvent2.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toTxt_anyEvent_returnsCorrectTxtRepresentation() {
        assertDoesNotThrow( () -> {
            String EventName = "Return Book";
            String start = "today";
            String end = "tomorrow";
            Event testEvent1 = new Event(EventName, start, end, false);
            String expected = "Event | false | Return Book | today | tomorrow";
            String actual = testEvent1.toTxt();
            assertEquals(expected, actual);

            Event testEvent2 = new Event(EventName, start, end, true);
            expected = "Event | true | Return Book | today | tomorrow";
            actual = testEvent2.toTxt();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void toTxt_dateTimeParse_success() {
        assertDoesNotThrow(() -> {
            String EventName = "Return Book";
            String start = "01-01-2024";
            String end = "02-01-2024";
            Event testEvent1 = new Event(EventName, start, end, false);
            String expected = "Event | false | Return Book | 2024-01-01T00:00 | 2024-01-02T00:00";
            String actual = testEvent1.toTxt();
            assertEquals(expected, actual);

            /* Can handle time and date parsing correctly */
            start = "01-01-2024 1800";
            end = "01-01-2024 19:35";
            Event testEvent2 = new Event(EventName, start, end, true);
            expected = "Event | true | Return Book | 2024-01-01T18:00 | 2024-01-01T19:35";
            actual = testEvent2.toTxt();
            assertEquals(expected, actual);

            /* Can handle time only parsing correctly */
            start = "1800";
            end = "19:35";
            Event testEvent3 = new Event(EventName, start, end, true);
            expected = "Event | true | Return Book | 18:00 | 19:35";
            actual = testEvent3.toTxt();
            assertEquals(expected, actual);
        });
    }
}
