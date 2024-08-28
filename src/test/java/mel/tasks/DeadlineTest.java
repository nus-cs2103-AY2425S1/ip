package mel.tasks;

import mel.exceptions.TaskException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void constructor_invalidInput_exceptionThrown() {
        try {
            assertEquals("", new Deadline("deadline something /by ?"));
            fail();
        } catch (TaskException e) {
            assertEquals("deadline <task> /by <date> <time>", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception caught: " + e.getMessage());
        }
    }

    @Test
    public void constructor_validInput_success() {
        try {
            assertEquals("[D][ ] something (by: 2 Feb 2022 12.00am)",
                    new Deadline("deadline something /by 2/2/22").toString());
        } catch (Exception e) {
            fail("Unexpected exception caught: " + e.getMessage());
        }
    }
}
