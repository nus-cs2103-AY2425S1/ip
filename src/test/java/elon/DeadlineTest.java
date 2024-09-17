package elon;

import elon.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Borrow book", false, LocalDate.of(2024, 10, 5));

        String result = deadline.toString();

        assertEquals("[D][ ] Borrow book (by: Oct 5 2024)", result);
    }

    @Test
    public void testToFileString() {
        Deadline deadline = new Deadline("Return book", true, LocalDate.of(2024, 10, 5));

        String result = deadline.toFileString();

        assertEquals("D | 1 | Return book | 2024-10-05", result);
    }
}