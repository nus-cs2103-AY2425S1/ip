package twilight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void setDone_alreadyDone_exceptionThrown() throws InvalidInputException {
        try {
            new Task("potato", true, "NA").setDone();
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nTask is already marked complete.", e.toString());
        }
    }

    @Test
    public void setUndone_done_success() throws InvalidInputException {
        Task t = new Task("test", true, "NA");
        t.setUndone();
        assertEquals("[ ] test" , t.toString());
    }

    @Test
    public void setUndone_notDone_exceptionThrown() throws InvalidInputException {
        try {
            new Task("potato", false, "NA").setUndone();
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nTask is already marked incomplete.", e.toString());
        }
    }

    @Test
    public void setDone_notDone_success() throws InvalidInputException {
        Task t = new Task("test", false, "NA");
        t.setDone();
        assertEquals("[X] test" , t.toString());
    }
}
