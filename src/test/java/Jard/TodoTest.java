package Jard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Read a book");
        assertEquals("Read a book", todo.getDescription());
    }

    @Test
    public void testTodoCreation2() {
        Todo todo = new Todo("Read a newspaper");
        assertEquals("Read a newspaper", todo.getDescription());
    }
}
