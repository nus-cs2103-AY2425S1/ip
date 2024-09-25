package Gumball;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void ToDo_emptyDescription_exceptionThrown() {
        try {
            ToDo temp = new ToDo("todo");
            fail();
        } catch (TaskException e) {
            assertEquals("Sorry! The description for a ToDo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void ToDo_simpleDescription_Success() throws TaskException {
        ToDo temp = new ToDo("todo test");
        assertEquals(temp.getDescription(), "test");
    }

}
