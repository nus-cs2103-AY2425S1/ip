package brainrot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ToDoTest {
    private ToDo todo;

    @BeforeEach
    public void setUp() {
        // Initialize the ToDo object before each test
        todo = new ToDo("Read book");
    }
    @Test
    public void testToString_notDone() {
        String expected = "[T][ ] Read book";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToFileString_done() {
        // Mark the task as done and then test the toFileString method
        todo.mark();
        String expected = "[T][X]/Read book";
        assertEquals(expected, todo.toFileString());
    }

}
