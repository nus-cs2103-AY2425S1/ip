package ollie.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link UnknownTaskTypeException} class.
 */
public class UnknownTaskTypeExceptionTest {

    /**
     * Tests the {@link UnknownTaskTypeException} constructor.
     * Verifies that the exception's message is correctly set.
     */
    @Test
    public void unknownTaskTypeExceptionTest() {
        UnknownTaskTypeException exception = new UnknownTaskTypeException();
        assertEquals("Oops! I don't recognize this task type. Please use 'todo', 'deadline', or 'event'. ☺", exception.getMessage());
    }
}