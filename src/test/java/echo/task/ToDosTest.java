package echo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    @Test
    public void testToString() {
        ToDos toDos = new ToDos("work");
        String expected = toDos.toString();
        String actual = "[T][ ] work";
        assertEquals(expected, actual);
    }

    @Test
    public void testToFancyString() {
        ToDos toDos = new ToDos("work");
        String expected = toDos.toFancyString();
        String actual = "ToDo | 0 | work";
        assertEquals(expected, actual);
    }

}
