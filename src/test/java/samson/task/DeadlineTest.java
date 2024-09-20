package samson.task;

import org.junit.jupiter.api.Test;
import samson.SamException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class DeadlineTest {

    @Test
    public void testDeadlineInitialization_withValidDate() throws SamException {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31 2359");

        assertEquals("Submit assignment", deadline.getDescription());
        assertFalse(deadline.isDone());
        assertEquals("[D][ ] Submit assignment (by: Dec 31 2024 23:59)", deadline.toString());
    }

    @Test
    public void testDeadlineInitialization_withInvalidDate() {
        SamException exception = assertThrows(SamException.class, () ->
                new Deadline("Submit assignment", "31-12-2024 2359")
        );
        assertEquals("Invalid date format! Please use yyyy-MM-dd HHmm.\n Example call: deadline your_task /by yyyy-MM-dd HHmm", exception.getMessage());
    }

    @Test
    public void testToStorageString_notDone() throws SamException {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31 2359");

        String expectedStorageString = "D | 0 | Submit assignment | 2024-12-31 2359";
        assertEquals(expectedStorageString, deadline.toStorageString());
    }

    @Test
    public void testToStorageString_done() throws SamException {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31 2359");
        deadline.complete();

        String expectedStorageString = "D | 1 | Submit assignment | 2024-12-31 2359";
        assertEquals(expectedStorageString, deadline.toStorageString());
    }

    @Test
    public void testToString_withValidDate() throws SamException {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31 2359");

        String expectedString = "[D][ ] Submit assignment (by: Dec 31 2024 23:59)";
        assertEquals(expectedString, deadline.toString());
    }
}

