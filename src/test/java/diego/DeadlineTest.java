package diego;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString_withTime() {
        Deadline deadline = new Deadline("submit report", "2023-09-30 2359");
        String expected = "[D][ ] submit report (by: Sep 30 2023, 11:59 pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testToString_withoutTime() {
        Deadline deadline = new Deadline("submit report", "2023-09-30");
        String expected = "[D][ ] submit report (by: Sep 30 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testToFileFormat_withTime() {
        Deadline deadline = new Deadline("submit report", "2023-09-30 2359");
        String expected = "D | 0 | submit report | 2023-09-30 2359";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    void testToFileFormat_withoutTime() {
        Deadline deadline = new Deadline("submit report", "2023-09-30");
        String expected = "D | 0 | submit report | 2023-09-30";
        assertEquals(expected, deadline.toFileFormat());
    }
}
