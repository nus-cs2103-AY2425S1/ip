package friday.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void markAsDone_setsTaskAsDone() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        event.markAsDone();
        Assertions.assertTrue(event.isDone());
    }

    @Test
    void toFileFormat_returnsCorrectFormat() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        Assertions.assertEquals("E | 0 | read book | 2024-08-24 1800 | 2024-08-24 2000", event.toFileFormat());
    }

    @Test
    void toString_returnsCorrectString() {
        Event event = new Event("read book", "2024-08-24 1800", "2024-08-24 2000");
        Assertions.assertEquals("[E][ ] read book (from: Aug 24 2024, 6:00 PM to: Aug 24 2024, 8:00 PM)",
                event.toString());
    }
}
