package rizzler;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    @Test
    public void toString_success() {
        Event testEvent = new Event("test 1",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        assertEquals(testEvent.toString(),
                "[E][ ] test 1 (from: Aug 15 2024 to: Aug 16 2024)");
    }

    @Test
    public void toSaveState_success() {
        Event testEvent = new Event("test 1",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        assertEquals(testEvent.toSaveState(),
                "E/0/test 1/2024-08-15/2024-08-16\n");
    }

    @Test
    public void compareTo_success() {
        // compare names
        Event testEvent1 = new Event("test 1",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        Event testEvent2 = new Event("test 2",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        assertTrue(testEvent1.compareTo(testEvent2) < 0);
        //compare start date
        Event testEvent3 = new Event("test 3",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        Event testEvent4 = new Event("test 3",
                LocalDate.parse("2024-08-16"),
                LocalDate.parse("2024-08-16"));
        assertTrue(testEvent3.compareTo(testEvent4) < 0);
        // compare end date
        Event testEvent5 = new Event("test 4",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-16"));
        Event testEvent6 = new Event("test 4",
                LocalDate.parse("2024-08-15"),
                LocalDate.parse("2024-08-17"));
        assertTrue(testEvent5.compareTo(testEvent6) < 0);
    }
}
