package myapp.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion(){
        assertEquals("[T][ ] go buy groceries",
                new Todo("go buy groceries").toString());
    }

    @Test
    public void testFileDataConversion(){
        assertEquals("T | 0 | go buy groceries",
                new Todo("go buy groceries").toFileFormat());
    }


}
