package colress.task;

import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ONE;
import static colress.testutil.TestUtil.VALID_FROM_TIME_TWO;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, false);
        assertEquals(event.toString(), String.format("[ ][E] %s (%s, %s to %s)", VALID_DESCRIPTION_ONE,
                VALID_DATE_ONE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                VALID_FROM_TIME_ONE.format(DateTimeFormatter.ofPattern("HHmm")),
                VALID_TO_TIME_ONE.format(DateTimeFormatter.ofPattern("HHmm"))));

        event = new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        assertEquals(event.toString(), String.format("[X][E] %s (%s, %s to %s)", VALID_DESCRIPTION_ONE,
                VALID_DATE_ONE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                VALID_FROM_TIME_ONE.format(DateTimeFormatter.ofPattern("HHmm")),
                VALID_TO_TIME_ONE.format(DateTimeFormatter.ofPattern("HHmm"))));
    }

    @Test
    public void toTextFileTest() {
        Event event = new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, false);
        assertEquals(event.toTextFile(),
                String.format("[ ] | EVENT | %s | %s | %s to %s", VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                        VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE));

        event = new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        assertEquals(event.toTextFile(),
                String.format("[X] | EVENT | %s | %s | %s to %s", VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                        VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE));
    }

    @Test
    public void equalsTest() {
        Event event = new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);

        // same values -> returns true
        assertTrue(event.equals(new Event(VALID_DESCRIPTION_ONE, VALID_DATE_ONE,
                VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE)));

        // same object -> returns true
        assertTrue(event.equals(event));

        // null -> returns false
        assertFalse(event.equals(null));

        // different types -> returns false
        assertFalse(event.equals(17));

        // different values -> returns false
        assertFalse(event.equals(new Event(VALID_DESCRIPTION_TWO, VALID_DATE_TWO,
                VALID_FROM_TIME_TWO, VALID_TO_TIME_TWO)));
    }
}
