package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ekud.exceptions.EkudException;

public class TodoTaskTest {
    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        EkudException e = assertThrows(EkudException.class, () -> new TodoTask(""));
        assertEquals(
                "I think you tried TODO nothing.\nI can only help to remind you of something todo.",
                e.getMessage());
    }

    @Test
    public void getSaveStringFormat() {
        try {
            Task t = new TodoTask("task name");
            // incomplete task
            assertEquals("T | 0 | LOW | task name", t.getSaveTaskString());
            t.markAsDone();
            // complete task
            assertEquals("T | 1 | LOW | task name", t.getSaveTaskString());
            t.setPriority(Task.Priority.HIGH);
            // set priority
            assertEquals("T | 1 | HIGH | task name", t.getSaveTaskString());

        } catch (EkudException e) {
            fail("Valid todo task construction failed");
        }
    }

    @Test
    public void toStringTest() {
        try {
            Task t = new TodoTask("task name");
            // incomplete task
            assertEquals("[T][ ] task name", t.toString());
            t.markAsDone();
            // complete task
            assertEquals("[T][X] task name", t.toString());
            t.setPriority(Task.Priority.HIGH);
            // set high priority
            assertEquals("[T][X] task name *HIGH PRIORITY*", t.toString());
        } catch (EkudException e) {
            fail("Valid todo task construction failed");
        }
    }
}
