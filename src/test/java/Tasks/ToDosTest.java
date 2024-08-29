package Tasks;

import Exceptions.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void dummyTest(){
        try {
            assertEquals("[T][ ] read book", new ToDos("read book").print());
        } catch (EmptyDescException e) {
            assertEquals("    OOPS!!! The description of a todo cannot be empty leh.", e.getMessage());
        }
    }
}
