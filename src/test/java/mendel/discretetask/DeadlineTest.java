package mendel.discretetask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void showDeadline() {
        assertEquals("[D][ ] testing (by: Jan 01 2024)",
                Deadline.of("deadline testing /by 1 Jan 2024").toString());
        assertEquals("[D][ ] testing (by: Jan 01 2024, 15:00)",
                Deadline.of("deadline testing /by 1 Jan 2024 1500").toString());
        assertEquals("[D][ ] testing (by: Jan 01 2024)",
                Deadline.of("deadline testing /by 01-01-2024").toString());
        assertEquals("[D][ ] testing (by: Jan 01 2024, 15:00)",
                Deadline.of("deadline testing /by 01-01-2024 15:00").toString());
    }

    @Test
    public void targetDue() {
        assertTrue(Deadline.of("deadline testing /by 01-01-2024 15:00").isTargetDueDate("01 Jan 2024"));
        assertFalse(Deadline.of("deadline testing /by 01-01-2024 15:00").isTargetDueDate("02 02 2024"));
    }

    @Test
    public void noDescriptionException() {
        try {
            Deadline.of("deadline /by 1 Jan 2024");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline description cannot be empty.\nAdd description.", e.toString());
        }
    }

    @Test
    public void noDateException() {
        try {
            Deadline.of("deadline testing");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline due cannot be empty.\nPlease indicate a due.", e.toString());
        }
    }

    @Test
    public void noDescriptionAndDateException() {
        try {
            Deadline.of("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline needs more details.\nAdd description and due.", e.toString());
        }
    }

    @Test
    public void unspacedByException() {
        try {
            Deadline.of("deadline testing/by 1 Jan 2024");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline due wrongly formatted\nPlease add spaces around /by", e.toString());
        }
    }
}
