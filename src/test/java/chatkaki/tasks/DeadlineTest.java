package chatkaki.tasks;

import chatkaki.tasks.Deadline;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void fileFormat_validDeadline_correctFormat() {
        Deadline deadline = new Deadline(false, "return book", LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals("DEADLINE,false,return book,2/12/2019 1800", deadline.fileFormat());
    }

    @Test
    public void toString_validDeadline_correctString() {
        Deadline deadline = new Deadline(false, "return book", LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals("[D][ ] return book (by: 2/12/2019 1800)", deadline.toString());
    }
}