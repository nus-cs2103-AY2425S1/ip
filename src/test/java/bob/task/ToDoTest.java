package bob.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        Task todo = new ToDo("a todo");
        assertEquals(todo.toString(), "[T][ ] a todo");

    }
    @Test
    public void testStringConversionForMarkedTodo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime dummy = LocalDateTime.parse("12/09/24 0200", formatter);

        assertEquals(
                new ToDo(
                        "a todo", true, dummy, dummy).toString(),
                "[T][X] a todo");
    }


}
