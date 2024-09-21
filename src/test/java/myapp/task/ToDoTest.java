package myapp.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] go buy groceries",
                new ToDo("go buy groceries").toString());
    }

    @Test
    public void testFileDataConversion() {
        assertEquals("T | 0 | go buy groceries",
                new ToDo("go buy groceries").toFileFormat());
    }


}
