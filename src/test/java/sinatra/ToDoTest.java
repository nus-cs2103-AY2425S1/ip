package sinatra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Unit tests for the ToDo class.
 */
public class ToDoTest {

    /**
     * Tests the newObjectFromData method.
     */
    @Test
    public void newObjectFromDataTest() {
        String input = "hi,true";
        ToDo output = sinatra.ToDo.newObjectFromData(input);
        ToDo actual = new sinatra.ToDo("hi", true);
        assertEquals(output.getContent(), actual.getContent());
        assertEquals(output.isMarked(), output.isMarked());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void toStringTest() {
        String input = "[T][ ] sing";
        ToDo actual = new sinatra.ToDo("sing", false);
        assertEquals(input, actual.toString());
    }
}
