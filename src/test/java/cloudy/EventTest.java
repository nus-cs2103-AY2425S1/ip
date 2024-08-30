package cloudy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testPrintTaskOnList_Marked() {
        Event event = new Event("go overseas", LocalDate.parse("2024-10-10"), LocalDate.parse("2024-10-15"),true);

        String result = event.printTaskOnList();

        assertEquals("[E][X] go overseas (from: 10 October 2024 to: 15 October 2024)", result);
    }

    @Test
    public void testPrintTaskOnList_Unmarked() {
        Event event = new Event("go overseas", LocalDate.parse("2024-10-10"), LocalDate.parse("2024-10-15"),false);

        String result = event.printTaskOnList();

        assertEquals("[E][ ] go overseas (from: 10 October 2024 to: 15 October 2024)", result);
    }

    @Test
    public void testToFileFormat_Marked() {
        Event event = new Event("go overseas", LocalDate.parse("2024-10-10"), LocalDate.parse("2024-10-15"),true);

        String result = event.toFileFormat();

        assertEquals("E | 1 | go overseas | 2024-10-10 | 2024-10-15", result);
    }

    @Test
    public void testToFileFormat_Unmarked() {
        Event event = new Event("go overseas", LocalDate.parse("2024-10-10"), LocalDate.parse("2024-10-15"),false);

        String result = event.toFileFormat();

        assertEquals("E | 0 | go overseas | 2024-10-10 | 2024-10-15", result);
    }
}
