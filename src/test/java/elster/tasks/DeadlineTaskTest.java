package elster.tasks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import elster.Elseption;



public class DeadlineTaskTest {
    @Test
    void ofTest() throws Elseption {
        Task task = DeadlineTask.of("deadline test desc /by 2023-08-30");

        assertEquals("[D][ ] test desc (by: 2023-08-30T23:59)", task.toString());
    }

    @Test
    public void of_onlyDeadline_errorMessageDisplayed() {
        try {
            DeadlineTask.of("deadline");
            fail();

        } catch (Elseption e) {
            assertEquals("Elster begs of you to have details for your task", e.getMessage());
        }
    }

    @Test
    public void of_noBy_errorMessageDisplayed() {
        try {
            DeadlineTask.of("deadline yada yada yada");
            fail();

        } catch (Elseption e) {
            assertEquals("Elster begs of you to have a yknow, deadline with /by", e.getMessage());
        }
    }

    @Test
    public void of_incorrectDateFormat_errorMessageDisplayed() {
        try {
            DeadlineTask.of("deadline test desc /by 2023-08-3011");
            fail();

        } catch (Elseption e) {
            assertEquals("for /by, Elster requires a yyyy-mm-dd or yyyy-mm-dd HH:mm "
                    + "format please and thanks",
                    e.getMessage()
            );
        }
    }
}
