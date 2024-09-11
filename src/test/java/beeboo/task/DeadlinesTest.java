package beeboo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import beeboo.exception.BeeBooExceptions;
import beeboo.exception.NoDescriptionException;


public class DeadlinesTest {
    @Test
    public void createDeadlineTest1() throws BeeBooExceptions {
        String test = "ca1 /by 2024-02-24";
        String expectedDeadlineString = "[D][ ] ca1(by:Feb 24 2024 at 12:00 am)";
        assertEquals(expectedDeadlineString, Deadlines.createDeadline(test).toString());
    }


    @Test
    public void createDeadlineTest2() {
        String test = " /by 2024-02-24";
        String expectedErrorMessage = "You didn't put in a description user. "
                + "Here is a list of commands and descriptions\n"
                + "event [eventName] /from [time] /to [time]\n"
                + "deadline [deadlineName]/ by [time]\n "
                + "todo [todoName]\n"
                + "Please try again user";
        assertEquals(expectedErrorMessage,
                assertThrows(NoDescriptionException.class, () -> {
                    Deadlines.createDeadline(test);
                }).toString());
    }

}