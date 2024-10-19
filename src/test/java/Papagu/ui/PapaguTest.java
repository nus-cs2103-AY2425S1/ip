package papagu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ToDos, Deadlines and Events classes
 */
public class PapaguTest {
    private ToDos todo;
    private Deadlines deadline;
    private Events event;

    /**
     * Creates tasks for testing
     * Creates tasks and storage for testing
     */
    @BeforeEach
    public void setUp() {
        todo = new ToDos("read book");
        deadline = new Deadlines("return book", LocalDate.parse("2021-06-07"), LocalTime.parse("18:00"));
        event = new Events("project meeting", LocalDate.parse("2024-08-10"),
                LocalTime.parse("14:00"), LocalTime.parse("16:00"));
    }

    @Test
    public void testTodo() {
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testDeadline() {
        assertEquals("[D][ ] return book (by: Jun-07-2021 18:00)", deadline.toString());
    }

    @Test
    public void testEvent() {
        assertEquals("[E][ ] project meeting (from: Aug-10-2024 14:00 to: 16:00)", event.toString());
    }

    @Test
    public void todoExceptionThrown() {
        IllegalTodoException exception = assertThrows(IllegalTodoException.class, () ->
            new ToDos(""));
        assertEquals("The description of a task cannot be empty.", exception.getMessage());
    }

    @Test
    public void deadlineExceptionThrown() {
        IllegalDeadlineException exception = assertThrows(IllegalDeadlineException.class, () ->
                new Deadlines("", LocalDate.parse("2021-06-07"), LocalTime.parse("18:00")));
        assertEquals("The description of a deadline cannot be empty.", exception.getMessage());
    }

    @Test
    public void eventExceptionThrown() {
        IllegalEventException exception = assertThrows(IllegalEventException.class, () ->
                new Events("", LocalDate.parse("2024-08-10"), LocalTime.parse("14:00"), LocalTime.parse("16:00")));
        assertEquals("The description of a event cannot be empty.", exception.getMessage());
    }
}
