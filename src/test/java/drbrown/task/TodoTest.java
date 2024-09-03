package drbrown.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private String description;

    @BeforeEach
    void setUp() {
        description = "Buy groceries";
    }

    @Test
    public void testSuccessfulCreationTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    void testToFileStringTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("T | false | Buy groceries", todo.toFileString());
    }

    @Test
    void testToUIStringTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("Doc, you don't just walk into a store and buy plutonium! But you sure can add this task to your list!\n", todo.toUIString());
    }

    @Test
    public void testSuccessfulCreationMarkDoneTodo() {
        Todo todo = new Todo(true, description);
        assertEquals("[T][X] Buy groceries", todo.toString());
    }
}
