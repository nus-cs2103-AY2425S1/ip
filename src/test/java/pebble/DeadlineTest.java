package pebble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void testValidDateFormat() {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-10");
        assertEquals("[D][ ] Submit assignment (by: Sep 10 2024)", deadline.toString());
    }

    @Test
    public void testInvalidDateFormat() {
        Deadline deadline = new Deadline("Submit assignment", "10th September");
        assertEquals("[D][ ] Submit assignment (by: 10th September)", deadline.toString());
    }

    @Test
    public void testEmptyDate() {
        Deadline deadline = new Deadline("Submit assignment", "");
        assertEquals("[D][ ] Submit assignment (by: )", deadline.toString());
    }

    @Test
    public void testNullDate() {
        Deadline deadline = new Deadline("Submit assignment", null);
        assertEquals("[D][ ] Submit assignment (by: null)", deadline.toString());
    }

    @Test
    public void testDateWithExtraSpaces() {
        Deadline deadline = new Deadline("Submit assignment", "   2024-09-10   ");
        assertEquals("[D][ ] Submit assignment (by: Sep 10 2024)", deadline.toString());
    }

    @Test
    public void testCustomDatePattern() {
        Deadline deadline = new Deadline("Submit project", "2024-12-31");
        assertEquals("[D][ ] Submit project (by: Dec 31 2024)", deadline.toString());
    }

    @Test
    public void testWhitespaceInDescription() {
        Deadline deadline = new Deadline("  Complete the report  ", "2024-08-31");
        assertEquals("[D][ ]   Complete the report   (by: Aug 31 2024)", deadline.toString());
    }

    @Test
    public void testNonDateString() {
        Deadline deadline = new Deadline("Attend meeting", "end of next week");
        assertEquals("[D][ ] Attend meeting (by: end of next week)", deadline.toString());
    }
}
