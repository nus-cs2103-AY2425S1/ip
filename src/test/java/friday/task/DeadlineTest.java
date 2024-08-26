package friday.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void markAsDone_setsTaskAsDone() {
        Deadline deadline = new Deadline("read book", "2024-08-24 1800");
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    @Test
    void toFileFormat_returnsCorrectFormat() {
        Deadline deadline = new Deadline("read book", "2024-08-24 1800");
        assertEquals("D | 0 | read book | 2024-08-24 1800", deadline.toFileFormat());
    }

    @Test
    void toString_returnsCorrectString() {
        Deadline deadline = new Deadline("read book", "2024-08-24 1800");
        assertEquals("[D][ ] read book (by: Aug 24 2024, 6:00 pm)", deadline.toString());
    }
}
