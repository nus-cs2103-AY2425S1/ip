package echo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        String[] toDoArray = { "work" };
        ToDo toDo = new ToDo(toDoArray);
        String expected = toDo.toString();
        String actual = "[T][ ] work";
        assertEquals(expected, actual);
    }

    @Test
    public void testToFancyString() {
        String[] toDoArray = { "work" };
        ToDo toDo = new ToDo(toDoArray);
        String expected = toDo.toFancyString();
        String actual = "ToDo | 0 | work";
        assertEquals(expected, actual);
    }

}
