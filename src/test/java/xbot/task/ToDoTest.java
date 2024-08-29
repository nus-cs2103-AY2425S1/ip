package xbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo task1 = new ToDo("test case 1");

    @Test
    public void Test1() {
        assertEquals("[T][ ] test case 1", task1.toString());
    }
}
