package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exception.CommandFoundButInvalidException;
public class EventsTest {
    @Test
    public void throwExceptionTest() {
        boolean isThrown = false;
        try {
            new Events("This is just a random String /start");
        } catch (CommandFoundButInvalidException e) {
            isThrown = true;
        }

        assertTrue(isThrown);
    }

    @Test
    public void testInvalidDateErrorMessage() {
        String expected = "Uh Oh, wrong syntax for the command - "
                + "event, please use yyyy-mm-ddThh:mm. E.g. 2024-09-11T23:59";
        String errorMsg = "";
        try {
            new Events("Test Event Time /from 2024-02-30T10:00 /to 2024-02-30T12:00");
        } catch (CommandFoundButInvalidException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(expected, errorMsg);
    }

    @Test
    public void testEmptyDescription() {
        String expected = "Bruh, you can't just type event. Give me more details.";
        String errorMsg = "";
        try {
            new Events("");
        } catch (CommandFoundButInvalidException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(expected, errorMsg);
    }
}
