package flychat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.InputMismatchException;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testCreateNewTodo_withValidDescriptionAndUnmarked() {
        String description = "Complete CS2030S Lecture 8";
        boolean isMarked = false;
        Todo todo = Todo.createNewTodo(description, isMarked);
        assertEquals("[T][ ] Complete CS2030S Lecture 8", todo.toString(),
                "The todo task should be created with the correct description and unmarked");
    }

    @Test
    void testCreateNewTodo_withValidDescriptionAndMarked() {
        String description = "Finish assignment";
        boolean isMarked = true;
        Todo todo = Todo.createNewTodo(description, isMarked);
        assertEquals("[T][X] Finish assignment", todo.toString(),
                "The todo task should be created with the correct description and marked");
    }

    @Test
    void testCreateNewTodo_withEmptyDescriptionThrowsException() {
        String description = "";
        boolean isMarked = false;
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
            Todo.createNewTodo(description, isMarked);
        });
        assertEquals("Please ensure that the input contains a description TT", exception.getMessage(),
                "The exception message should match the expected output");
    }
}
