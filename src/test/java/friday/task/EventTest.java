package friday.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void markAsDone_setsTaskAsDone() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        event.markAsDone();
        assertTrue(event.isDone());
    }

    @Test
    void toFileFormat_returnsCorrectFormat() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        assertEquals("E | 0 | read book | 2024-08-24 1800 | 2024-08-24 2000", event.toFileFormat());
    }

    @Test
    void toString_returnsCorrectString() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        assertEquals("[E][ ] read book (from: Aug 24 2024, 6:00 pm to: Aug 24 2024, 8:00 pm)", event.toString());
    }
}
