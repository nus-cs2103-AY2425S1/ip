package colress.task;

import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE, false);
        assertEquals(deadline.toString(), String.format("[ ][D] %s (%s)", VALID_DESCRIPTION_ONE,
                VALID_DATE_ONE.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));

        deadline = new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE, true);
        assertEquals(deadline.toString(), String.format("[X][D] %s (%s)", VALID_DESCRIPTION_ONE,
                VALID_DATE_ONE.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
    }

    @Test
    public void toTextFileTest() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE, false);
        assertEquals(deadline.toTextFile(),
                String.format("[ ] | DEADLINE | %s | %s", VALID_DESCRIPTION_ONE, VALID_DATE_ONE));

        deadline = new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE, true);
        assertEquals(deadline.toTextFile(),
                String.format("[X] | DEADLINE | %s | %s", VALID_DESCRIPTION_ONE, VALID_DATE_ONE));
    }

    @Test
    public void equalsTest() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE);

        // same values -> returns true
        assertTrue(deadline.equals(new Deadline(VALID_DESCRIPTION_ONE, VALID_DATE_ONE)));

        // same object -> returns true
        assertTrue(deadline.equals(deadline));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different types -> returns false
        assertFalse(deadline.equals(17));

        // different values -> returns false
        assertFalse(deadline.equals(new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_TWO)));
    }
}
