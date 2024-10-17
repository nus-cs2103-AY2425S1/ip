package ollie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link EmptyDescriptionException} class.
 */
public class EmptyDescriptionExceptionTest {

    /**
     * Tests the {@link EmptyDescriptionException} constructor with different task types.
     * Verifies that the exception's message is correctly set based on the task type.
     */
    @Test
    public void emptyDescriptionExceptionTest_withTodoTask() {
        EmptyDescriptionException exception = new EmptyDescriptionException("TODO");
        assertEquals("Please add a name for a TODO task! ☺", exception.getMessage());
    }

    @Test
    public void emptyDescriptionExceptionTest_withDeadlineTask() {
        EmptyDescriptionException exception = new EmptyDescriptionException("DEADLINE");
        assertEquals("Please add a name for a DEADLINE task! ☺", exception.getMessage());
    }

    @Test
    public void emptyDescriptionExceptionTest_withEventTask() {
        EmptyDescriptionException exception = new EmptyDescriptionException("EVENT");
        assertEquals("Please add a name for a EVENT task! ☺", exception.getMessage());
    }
}
