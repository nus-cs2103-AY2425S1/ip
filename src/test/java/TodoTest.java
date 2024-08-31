package bob;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals(
                new ToDo(
                        "a todo").toString(),
                "[T][ ] a todo");

    }
    @Test
    public void testStringConversionForMarkedTodo() {
        assertEquals(
                new ToDo(
                        "a todo", true).toString(),
                "[T][X] a todo");
    }





}
