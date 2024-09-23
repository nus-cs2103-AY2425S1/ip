package xbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo task1 = new ToDo("test case 1");

    @Test
    public void testTodoOutput() {
        assertEquals("[T][ ] test case 1", task1.toString());
    }
}
