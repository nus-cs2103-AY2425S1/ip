package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bobby.exception.EmptyDescriptionException;
import bobby.tasks.Todo;

/**
 * Tests for {@link Todo} class.
 */
public class TodoTest {

    /**
     * Tests the {@link Todo#createTodo(String)} method with correct input.
     * Validates that a Todo task is correctly created.
     */
    @Test
    public void validTodoTask() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("Watch Lecture", todoTask.getDescription());
        assertEquals("T", todoTask.getTaskType());
    }

    /**
     * Tests the {@link Todo#toString()} method.
     * Validates that a Todo task is correctly formatted as a string.
     */
    @Test public void validTodoStringFormat() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("[T][ ] Watch Lecture", todoTask.toString());
    }


    /**
     * Tests {@link Todo#taskInFile()} method.
     * Validates that a Todo task is correctly formatted in a file.
     */
    @Test
    public void validTodoTaskFormatInFile() throws EmptyDescriptionException {
        String input = "todo Watch Lecture";
        Todo todoTask = Todo.createTodo(input);
        assertEquals("T |  | Watch Lecture", todoTask.taskInFile());
    }

    /**
     * Tests the {@link Todo#createTodo(String)} method with incorrect input.
     * Validates that a Todo task is not created with empty description.
     */
    @Test
    public void invalidTodoTask() throws EmptyDescriptionException {
        String input = "todo";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> Todo.createTodo(input));
    }
}
