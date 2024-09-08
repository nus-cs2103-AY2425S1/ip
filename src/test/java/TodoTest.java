import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import skibidi.task.AbstractTask;
import skibidi.task.AbstractTask.TaskDeserializationException;
import skibidi.task.AbstractTask.TaskValidationException;
import skibidi.task.Todo;

public class TodoTest {
    @Test
    public void testSerializationFormat_serializedCorrectly() {
        Todo todo = new Todo("Finish some chores.");
        String expected = todo.toString();
        try {
            String deserializedTodo = AbstractTask.deserialize(todo.serialize()).toString();
            Assertions.assertEquals(expected, deserializedTodo);
        } catch (TaskDeserializationException e) {
            Assertions.fail("TaskDeserializationException was thrown unexpectedly: " + e.getMessage());
        }
    }

    @Test
    public void testTodoValidation_todoCreatedCorrectly() {
        String placeholderDescription = "Finish some chores.";
        Todo todo = new Todo(placeholderDescription);
        String expected = "[T][ ] " + placeholderDescription;
        Assertions.assertEquals(expected, todo.toString());
    }

    @Test
    public void testTodoValidation_extraArguments_exceptionThrown() {
        String[] args = {"description", "extra information"};
        TaskValidationException thrown = Assertions.assertThrows(
                TaskValidationException.class,
                Todo::validateThenCreate,
                "Expected TaskValidationException to be thrown for extra input arguments.");
        String expectedMessage = "TASK VALIDATION ERROR: Invalid number of arguments given for Todo!";
        Assertions.assertEquals(expectedMessage,
                thrown.getMessage(),
                String.format("Expected error message %s", expectedMessage));
    }
}
