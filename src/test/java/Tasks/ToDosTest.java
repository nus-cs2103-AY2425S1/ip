package Tasks;

import Exceptions.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void todoPrintCheck(){
        try {
            assertEquals("[T][ ] read book", new ToDos("read book").print());
        } catch (EmptyDescException e) {
            assertEquals("    OOPS!!! The description of a todo cannot be empty leh.", e.getMessage());
        }
    }

    @Test
    public void invalidTodoCheck(){
        try {
            assertEquals("[T][ ] read book", new ToDos("").print());
        } catch (EmptyDescException e) {
            assertEquals("     OOPS!!! The description of a todo cannot be empty leh. Pls provide in the following format: todo read book", e.getMessage());
        }
    }
}
