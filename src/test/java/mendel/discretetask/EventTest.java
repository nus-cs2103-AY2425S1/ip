package mendel.discretetask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void showDeadline() {
        assertEquals("[E][ ] testing (from: Jan 01 2024 to Jan 01 2024)",
                Event.of("event testing /from 1 Jan 2024 /to 1 Jan 2024").toString());
        assertEquals("[E][ ] testing two (from: Jan 01 2024, 15:00 to Jan 01 2024, 15:00)",
                Event.of("event testing two /from 1 Jan 2024 1500 /to 1 Jan 2024 1500").toString());
        assertEquals("[E][ ] testing three (from: Jan 01 2024 to Jan 01 2024)",
                Event.of("event testing three /from 01-01-2024 /to 01-01-2024").toString());
        assertEquals("[E][ ] testing four (from: Jan 01 2024, 15:00 to Jan 01 2024, 15:00)",
                Event.of("event testing four /from 01-01-2024 15:00 /to 01-01-2024 15:00").toString());
    }

    @Test
    public void noDescriptionException() {
        try {
            Event.of("event /from 1 Jan 2024 /to 1 Jan 2024");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! event description cannot be empty.\nAdd description.", e.toString());
        }
    }

    @Test
    public void noStartDateException() {
        try {
            Event.of("event testing /from 1 Jan 2024");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of end.\nPlease specify an end.", e.toString());
        }
    }

    @Test
    public void noEndDateException() {
        try {
            Event.of("event testing /to 1 Jan 2024");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of start.\nPlease specify only one start.", e.toString());
        }
    }

    @Test
    public void noDateException() {
        try {
            Event.of("event testing");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of start.\nPlease specify only one start.", e.toString());
        }
    }
}
