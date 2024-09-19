package shenhe.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shenhe.task.Todo;

/**
 * Unit tests for the EmptyTaskDescriptionException class.
 */
public class EmptyTaskDescriptionExceptionTest {
    @Test
    public void emptyTaskDescription_throwsEmptyTaskDescriptionException() {
        // Simulate a method or situation where the exception would be thrown
        assertThrows(EmptyTaskDescriptionException.class, () -> {
            // Assuming that creating a task with an empty description should throw this exception
            createTask("");
        });
    }

    @Test
    public void emptyTaskDescription_exceptionMessageIsCorrect() {
        try {
            // Attempt to create a task with an empty description, which will throw the exception
            createTask("");
        } catch (EmptyTaskDescriptionException e) {
            // Ensure the exception message matches the expected default message
            assertEquals("My dear traveller, the task description cannot be empty. Please give me "
                    + "something specific.", e.getMessage());
        }
    }

    /**
     * Simulates creating a task and throws an EmptyTaskDescriptionException if the description is empty.
     *
     * @param description Task description
     * @throws EmptyTaskDescriptionException if the task description is empty
     */
    private void createTask(String description) throws EmptyTaskDescriptionException {
        if (description.trim().isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        // Normally, you would create a task here (e.g., new Todo or other Task objects)
        new Todo(description, true);
    }
}
