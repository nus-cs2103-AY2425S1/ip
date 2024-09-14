package rizzler;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void toString_success() throws Exception {
        Deadline testDeadline = new Deadline("test 1", LocalDate.parse("2024-08-15"));
        assertEquals(testDeadline.toString(),
                "[D][ ] test 1 (by: Aug 15 2024)");
    }

    @Test
    public void toSaveState_success() throws Exception {
        Deadline testDeadline = new Deadline("test 2", LocalDate.parse("2024-08-15"));
        assertEquals(testDeadline.toSaveState(), "D/0/test 2/2024-08-15\n");
    }

    @Test
    public void compareTo_success() throws Exception {
        // compare naming
        Deadline testDeadline1 = new Deadline("test 3", LocalDate.parse("2024-08-15"));
        Deadline testDeadline2 = new Deadline("test 4", LocalDate.parse("2024-08-15"));
        assertTrue(testDeadline1.compareTo(testDeadline2) < 0);
        // compare time
        Deadline testDeadline3 = new Deadline("test 5", LocalDate.parse("2024-08-15"));
        Deadline testDeadline4 = new Deadline("test 5", LocalDate.parse("2024-08-16"));
        assertTrue(testDeadline3.compareTo(testDeadline4) < 0);
    }
}
