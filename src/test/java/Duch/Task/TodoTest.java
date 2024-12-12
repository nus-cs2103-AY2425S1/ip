package duch.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("return book", false);
        String expected = "[T][ ] return book";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToStore() {
        Todo todo = new Todo("return book", false);
        String expected = "T|0|return book";
        assertEquals(expected, todo.toStore());
    }
}
