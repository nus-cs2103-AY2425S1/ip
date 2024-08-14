package sigma;

import org.junit.jupiter.api.Test;
import sigma.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTestOne() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime by = LocalDateTime.parse("24/08/24 15:00", formatter);
        Deadline test = new Deadline("Test", by);
        assertEquals("[D][ ] Test (by: 24 Aug 2024 15:00)", test.toString(), "toString() ok");
        assertEquals("D | 0 | Test | 24 Aug 2024 15:00", test.stringify(), "stringify() ok");

        test.markAsDone();
        assertEquals("[D][X] Test (by: 24 Aug 2024 15:00)", test.toString(),
                "toString() after markAsDone() ok");
        assertEquals("D | 1 | Test | 24 Aug 2024 15:00", test.stringify(),
                "stringify() after markAsDone() ok");
    }

    @Test
    public void deadlineTestTwo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime by = LocalDateTime.parse("12/12/12 12:12", formatter);
        Deadline test = new Deadline("Testing", by);
        test.markAsDone();
        assertEquals("[D][X] Testing (by: 12 Dec 2012 12:12)", test.toString(),
                "toString() after markAsDone() ok");
        test.markAsNotDone();
        assertEquals("D | 0 | Testing | 12 Dec 2012 12:12", test.stringify(),
                "stringify() after markAsNotDone() ok");
    }
}
