import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class DeadlineTest {
    DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    @Test
    public void testDeadlineCreationAndFormatting() {
        Deadline deadline = new Deadline("Submit assignment", LocalDateTime.parse("20/8/2024 2359", INPUT_DATE_FORMAT));
        assertEquals("[D][ ] Submit assignment (by: Aug 20 2024, 11:59PM)", deadline.toString());
    }

    @Test
    public void testDeadlineFileFormat() {
        Deadline deadline = new Deadline("Complete project report", LocalDateTime.parse("30/8/2024 1700", INPUT_DATE_FORMAT));
        assertEquals("D | 0 | Complete project report | Aug 30 2024, 5:00PM", deadline.toFileFormat());
    }

    @Test
    public void testDeadlineMarkAsDone() {
        Deadline deadline = new Deadline("Finish coding task", LocalDateTime.parse("25/8/2024 1800", INPUT_DATE_FORMAT));
        deadline.markDone();
        assertEquals("[D][X] Finish coding task (by: Aug 25 2024, 6:00PM)", deadline.toString());
        assertEquals("D | 1 | Finish coding task | Aug 25 2024, 6:00PM", deadline.toFileFormat());
    }


}

