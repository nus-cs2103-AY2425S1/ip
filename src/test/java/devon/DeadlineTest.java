package devon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dbReadableFormat_undone_success() {
        Deadline deadline = new Deadline(
                "desc",
                LocalDateTime.parse("2024-01-01 1234", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
        );
        assertEquals("Deadline|0|desc|2024-01-01T12:34", deadline.dbReadableFormat());
    }

    @Test
    public void toString_undone_success() {
        Deadline deadline = new Deadline(
                "desc",
                LocalDateTime.parse("2024-01-01 1234", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
        );
        assertEquals("[D][ ] desc (by: Jan 1 2024, 12:34 pm)", deadline.toString());
    }

    @Test
    public void dbReadableFormat_done_success() {
        Deadline deadline = new Deadline(
                "desc",
                LocalDateTime.parse("2024-01-01 1234", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
        );
        deadline.markAsDone();
        assertEquals("Deadline|1|desc|2024-01-01T12:34", deadline.dbReadableFormat());
    }

    @Test
    public void toString_done_success() {
        Deadline deadline = new Deadline(
                "desc",
                LocalDateTime.parse("2024-01-01 1234", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
        );
        deadline.markAsDone();
        assertEquals("[D][X] desc (by: Jan 1 2024, 12:34 pm)", deadline.toString());
    }
}
