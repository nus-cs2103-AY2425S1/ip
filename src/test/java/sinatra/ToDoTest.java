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

    /**
     * Tests the getDataForStorage method.
     */
    @Test
    public void getDataForStorageTest() {
        ToDo todo = new sinatra.ToDo("read book", true);
        String expected = "Sinatra.ToDo:read book,True";
        assertEquals(expected, todo.getDataForStorage());
    }

    /**
     * Tests the constructor with different inputs.
     */
    @Test
    public void constructorTest() {
        ToDo todo1 = new sinatra.ToDo("write code", false);
        assertEquals("write code", todo1.getContent());
        assertEquals(false, todo1.isMarked());

        ToDo todo2 = new sinatra.ToDo("exercise", true);
        assertEquals("exercise", todo2.getContent());
        assertEquals(true, todo2.isMarked());
    }

    /**
     * Tests the toString method with different statuses.
     */
    @Test
    public void toStringWithStatusTest() {
        ToDo todo1 = new sinatra.ToDo("cook dinner", true);
        assertEquals("[T][X] cook dinner", todo1.toString());

        ToDo todo2 = new sinatra.ToDo("clean room", false);
        assertEquals("[T][ ] clean room", todo2.toString());
    }

}
