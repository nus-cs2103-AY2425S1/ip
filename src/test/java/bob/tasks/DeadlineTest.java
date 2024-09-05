package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineExportTest() {
        Deadline deadlineWithDate = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("deadline false Test /by 12/01/2001", deadlineWithDate.export());

        Deadline deadlineWithDateTime = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("deadline false Test /by 12/01/2001 1800", deadlineWithDateTime.export());

        Deadline deadlineWithDateMarked = new Deadline("Test", LocalDate.parse("12/01/2001",
                DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        deadlineWithDateMarked.mark();
        assertEquals("deadline true Test /by 12/01/2001", deadlineWithDateMarked.export());

        Deadline deadlineWithDateTimeMarked = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        deadlineWithDateTimeMarked.mark();
        assertEquals("deadline true Test /by 12/01/2001 1800", deadlineWithDateTimeMarked.export());
    }

    @Test
    public void deadlineToStringTest() {
        Deadline deadlineWithDate = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("[D][ ] Test (by: Jan 12 2001)", deadlineWithDate.toString());

        Deadline deadlineWithDateTime = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("[D][ ] Test (by: Jan 12 2001 18:00)", deadlineWithDateTime.toString());

        Deadline deadlineWithDateMarked = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        deadlineWithDateMarked.mark();
        assertEquals("[D][X] Test (by: Jan 12 2001)", deadlineWithDateMarked.toString());

        Deadline deadlineWithDateTimeMarked = new Deadline("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        deadlineWithDateTimeMarked.mark();
        assertEquals("[D][X] Test (by: Jan 12 2001 18:00)", deadlineWithDateTimeMarked.toString());
    }

}
