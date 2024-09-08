import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import skibidi.task.AbstractTask;
import skibidi.task.AbstractTask.TaskDeserializationException;

public class AbstractTaskTest {
    @Test
    public void deserialize_emptyInput_exceptionThrown() {
        String emptyString = "";
        TaskDeserializationException thrown = Assertions.assertThrows(
                TaskDeserializationException.class, () ->
                        AbstractTask.deserialize(emptyString),
                "Expected TaskDeserializationException to be thrown for empty string.");
        String expectedMessage = "TASK PARSING ERROR: No task type matches the given indicator!";
        Assertions.assertEquals(expectedMessage,
                thrown.getMessage(),
                String.format("Expected error message %s", expectedMessage));
    }

    @Test
    public void deserialize_invalidTodoFormat_exceptionThrown() {
        String invalidString = "T|inoasdf";
        TaskDeserializationException thrown = Assertions.assertThrows(
                TaskDeserializationException.class, () ->
                AbstractTask.deserialize(invalidString),
                "Expected TaskDeserializationException to be thrown for invalid Todo string.");
        String expectedMessage = "TASK PARSING ERROR: Invalid format given for Todo!";
        Assertions.assertEquals(expectedMessage,
                thrown.getMessage(),
                String.format("Expected error message %s", expectedMessage));
    }
}
