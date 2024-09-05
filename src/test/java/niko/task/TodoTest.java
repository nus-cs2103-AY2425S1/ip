package niko.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Write tests");
        String expected = "[T][ ] Write tests";
        assertEquals(expected, todo.toString());
    }
}
