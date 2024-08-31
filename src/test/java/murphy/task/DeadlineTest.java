package murphy.task;

import murphy.MurphyException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadlineConstructor_emptyBy_exceptionThrown() {
        try {
            new Deadline("test", "");
            fail();
        } catch (MurphyException e) {
            assertEquals("Deadline by date cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void deadlineConstructor_invalidDate_exceptionThrown() {
        try {
            new Deadline("test", "not a date");
            fail();
        } catch (MurphyException e) {
            assertEquals("Date should be in the format yyyy-mm-dd.", e.getMessage());
        }
    }
}
