package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bobby.exception.EmptyDescriptionException;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void validTodoTask() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("Watch Lecture", todoTask.getDescription());
        assertEquals("T", todoTask.getTaskType());
    }

    @Test public void validTodoStringFormat() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("[T][ ] Watch Lecture", todoTask.toString());
    }

    @Test
    public void validTodoTaskFormatInFile() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("T |  | Watch Lecture", todoTask.taskInFile());
    }

    @Test
    public void invalidTodoTask() throws EmptyDescriptionException {
        String input = "todo";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> Todo.createTodo(input));
    }
}
