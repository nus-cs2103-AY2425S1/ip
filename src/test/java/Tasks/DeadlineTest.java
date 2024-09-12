package Tasks;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlinePrintCheck(){
        try {
            assertEquals("[D][ ] read book (by: Apr 10 2020, 4:00 pm)", new Deadlines("read book /by 2020-04-10 16:00").print());
        } catch (EmptyDeadlineDateException e) {
            assertEquals("     OOPS!! Deadline date not given leh! " +
                    "Pls provide in the following format: " +
                    "deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00", e.getMessage());
        } catch (EmptyDeadlineException e) {
            assertEquals("     OOPS!!! The description of a deadline cannot be empty leh. Pls provide in the following format: deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00", e.getMessage());
        }
    }

    @Test
    public void invalidDeadlineCheck1() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020", new Deadlines("").print());
        } catch (EmptyDeadlineException | EmptyDeadlineDateException e) {
            assertEquals("     OOPS!!! The description of a deadline cannot be empty leh. Pls provide in the following format: deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00", e.getMessage());
        }
    }

    @Test
    public void invalidDeadlineCheck2() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020", new Deadlines("read book /by ").print());
        } catch (EmptyDeadlineException | EmptyDeadlineDateException e)  {
            assertEquals("     OOPS!! Deadline date not given leh! " +
                    "Pls provide in the following format: " +
                    "deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00", e.getMessage());
        }
    }
}
