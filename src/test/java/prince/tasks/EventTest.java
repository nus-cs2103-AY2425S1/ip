package prince.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.tasks.Event;

public class EventTest {
    @Test
    public void constructor_validInputWithoutFormattedDate_success() {
        Event event = new Event("test method", "9 aug 6pm", "12am");
        assertEquals("test method", event.getDescription());
        assertEquals("9 aug 6pm", event.toFileFormat().split(" .. ")[3]);
        assertEquals("12am", event.toFileFormat().split(" .. ")[4]);
    }

    @Test
    public void toString_validInputWithFormattedDate_success() {
        Event event = new Event("test method", "2024-08-31", "2024-09-15");
        String expected = "[E][ ]  test method (from: 31 Aug 2024 to: 15 Sep 2024)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toString_validInputWithFormattedDateTime_success() {
        Event event = new Event("test method", "2024-08-31 1832", "2024-08-31 2111");
        String expected = "[E][ ]  test method (from: 31 Aug 2024 6:32pm to: 31 Aug 2024 9:11pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toString_validInputWithoutFormattedDateTime_success() {
        Event event = new Event("test method", "sunday 5am", "12pm");
        String expected = "[E][ ]  test method (from: sunday 5am to: 12pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toFileFormat_validInput_success() {
        Event event = new Event("test method", "sunday 5am", "12pm");
        String expected = "E .. 0 .. test method .. sunday 5am .. 12pm";
        assertEquals(expected, event.toFileFormat());
    }
}
