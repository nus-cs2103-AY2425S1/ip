package Tasks;

import Exceptions.EmptyDeadlineDateException;
import Exceptions.EmptyDeadlineException;
import Exceptions.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest(){
        try {
            assertEquals("[D][ ] read book (by: Apr 10 2020, 4:00 PM)", new Deadlines("read book /by 2020-04-10 16:00").print());
        } catch (EmptyDeadlineDateException e) {
            assertEquals("     OOPS!! Deadline date not given leh!", e.getMessage());
        } catch (EmptyDeadlineException e) {
            assertEquals("     OOPS!!! The description of a deadline cannot be empty leh.", e.getMessage());
        }
    }
}
