import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import skibidi.task.AbstractTask;
import skibidi.task.AbstractTask.TaskDeserializationException;
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
}
