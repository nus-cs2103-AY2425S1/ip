package echo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo toDos = new ToDo("work");
        String expected = toDos.toString();
        String actual = "[T][ ] work";
        assertEquals(expected, actual);
    }

    @Test
    public void testToFancyString() {
        ToDo toDos = new ToDo("work");
        String expected = toDos.toFancyString();
        String actual = "ToDo | 0 | work";
        assertEquals(expected, actual);
    }

}
