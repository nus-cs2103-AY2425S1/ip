package botty.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import botty.exceptions.BottyException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

public class EventTest {
    @Test
    public void eventCreation_validInputs_success() throws BottyException {
        Event event = new Event(false, "description", "2024-11-29", "2024-11-30");
        assertEquals("[E] [ ] description (from: 29 Nov 2024 to: 30 Nov 2024)", event.toString());
    }
    @Test
    public void eventCreation_emptyDescription_exceptionThrown() throws IncorrectDateFormatException {
        try {
            new Event(false, "", "2014-03-13", "2014-04-01");
            fail();
        } catch (EmptyArgumentException e) {
            assertEquals("The following argument is empty: description", e.getMessage());
        }
    }
    @Test
    public void eventCreation_incorrectDateFormat_exceptionThrown() throws EmptyArgumentException {
        try {
            new Event(false, "description", "2014-03-13", "2014-4-15");
            fail();
        } catch (IncorrectDateFormatException e) {
            assertEquals("end date needs to be in format yyyy-mm-dd", e.getMessage());
        }
    }
}
