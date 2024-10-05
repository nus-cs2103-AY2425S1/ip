package pebble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the Deadline class with valid date format
     */
    @Test
    public void testValidDateFormat() {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-10");
        assertEquals("[D][ ] Submit assignment (by: Sep 10 2024)", deadline.toString());
    }

    /**
     * Tests the Deadline class with invalid date format
     */
    @Test
    public void testInvalidDateFormat() {
        Deadline deadline = new Deadline("Submit assignment", "10th September");
        assertEquals("[D][ ] Submit assignment (by: 10th September)", deadline.toString());
    }

    /**
     * Tests the Deadline class with empty date
     */
    @Test
    public void testEmptyDate() {
        Deadline deadline = new Deadline("Submit assignment", "");
        assertEquals("[D][ ] Submit assignment (by: )", deadline.toString());
    }

    /**
     * Tests the Deadline class with null date
     */
    @Test
    public void testNullDate() {
        Deadline deadline = new Deadline("Submit assignment", null);
        assertEquals("[D][ ] Submit assignment (by: null)", deadline.toString());
    }

    /**
     * Tests the Deadline class with extra spaces in date
     */
    @Test
    public void testDateWithExtraSpaces() {
        Deadline deadline = new Deadline("Submit assignment", "   2024-09-10   ");
        assertEquals("[D][ ] Submit assignment (by: Sep 10 2024)", deadline.toString());
    }

    /**
     * Tests the Deadline class with valid date pattern
     */
    @Test
    public void testCustomDatePattern() {
        Deadline deadline = new Deadline("Submit project", "2024-12-31");
        assertEquals("[D][ ] Submit project (by: Dec 31 2024)", deadline.toString());
    }

    /**
     * Tests the Deadline class with extra spaces in description
     */
    @Test
    public void testWhitespaceInDescription() {
        Deadline deadline = new Deadline("  Complete the report  ", "2024-08-31");
        assertEquals("[D][ ]   Complete the report   (by: Aug 31 2024)", deadline.toString());
    }

    /**
     * Tests the Deadline class with non date string
     */
    @Test
    public void testNonDateString() {
        Deadline deadline = new Deadline("Attend meeting", "end of next week");
        assertEquals("[D][ ] Attend meeting (by: end of next week)", deadline.toString());
    }
}
