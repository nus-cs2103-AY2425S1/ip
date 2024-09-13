package mel.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import mel.exceptions.TaskException;

public class DeadlineTest {
    @Test
    public void constructor_invalidDate_exceptionThrown() {
        try {
            assertEquals("",
                    new Deadline("deadline something /by 30-2-2022").toString());
            fail();
        } catch (TaskException e) {
            assertEquals("deadline <task> /by <date> <time>", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception caught: " + e.getMessage());
        }
    }

    @Test
    public void constructor_invalidFormat_exceptionThrown() {
        try {
            assertEquals("", new Deadline("deadline /by 22-2-22").toString());
            fail();
        } catch (TaskException e) {
            assertEquals("deadline <task> /by <date> <time>", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception caught: " + e.getMessage());
        }
    }

    @Test
    public void constructor_invalidInput_exceptionThrown() {
        try {
            assertEquals("", new Deadline("deadline something /by ?").toString());
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
