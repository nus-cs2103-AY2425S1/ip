package zero;

import zero.task.Task;
import zero.task.Todo;
import zero.util.Parser;
import zero.exception.ZeroException;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTask() {
        Task task = new Task("Test task");
        assertEquals("[ ] Test task", task.toString());
    }

    @Test
    public void testTodo() {
        Todo todo = new Todo("Study");
        assertEquals("[T][ ] Study", todo.toString());
    }

    @Test
    public void testDate() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 9, 1, 14, 30);
        try {
            assertEquals(dateTime, Parser.handleDate("2024-09-01 1430"));
        } catch (ZeroException e) {
            System.out.println(e.getMessage());
        }

    }

}