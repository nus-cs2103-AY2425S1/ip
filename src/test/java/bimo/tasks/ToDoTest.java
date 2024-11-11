package bimo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        Task todo = new ToDo("Attend lesson");
        assertEquals("[T][ ] Attend lesson", todo.toString());
    }
}
