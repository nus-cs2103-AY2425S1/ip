package elster.tasks;

import elster.Elseption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    void ofTest() throws Elseption {
        Task task = DeadlineTask.of("deadline test desc /by 2023-08-30");

        assert task != null;
        assertEquals("[D][ ] test desc (by: 2023-08-30T23:59)", task.toString());
    }

    @Test
    public void of_onlyDeadline_errorMessageDisplayed() {
        assertEquals(null, DeadlineTask.of("deadline"));
    }

    @Test
    public void of_noBy_errorMessageDisplayed() {
        assertEquals(null, DeadlineTask.of("deadline yada yada yada"));
    }

    @Test
    public void of_incorrectDateFormat_errorMessageDisplayed() {
        assertEquals(null, DeadlineTask.of("deadline test desc /by 2023-08-3011"));
    }
}
