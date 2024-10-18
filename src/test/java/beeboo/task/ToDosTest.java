package beeboo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import beeboo.exception.BeeBooExceptions;
import beeboo.exception.NoDescriptionException;


public class ToDosTest {
    @Test
    public void createToDosTest1() throws BeeBooExceptions {
        String test = "borrow book";
        String expected = "[T][ ] borrow book";

        assertEquals(expected, ToDos.createToDo(test).toString());
    }

    @Test
    public void createToDosTest2() {
        String expectedErrorMessage = "You didn't put in a description user. "
                + "Here is a list of commands and descriptions\n"
                + "event [eventName] /from [time] /to [time]\n"
                + "deadline [deadlineName]/ by [time]\n "
                + "todo [todoName]\n"
                + "Please try again user";
        String test = "";
        assertEquals(expectedErrorMessage, assertThrows(NoDescriptionException.class, () -> {
            ToDos.createToDo(test);
        }).toString());
    }

}
