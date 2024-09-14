package beechat.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test //Test that a TodoTask object is correctly created with the specified name.
    public void testTodoTaskCreation() {
        TodoTask todo = new TodoTask("Read book");
        assertEquals("Read book", todo.description, "TodoTask name should be 'Read book'.");
    }

    @Test //Test that marking a TodoTask as done updates its string representation.
    public void testMarkAsDone() {
        TodoTask todo = new TodoTask("Read book");
        todo.mark();
        assertEquals("[T][X] Read book", todo.toString(), "TodoTask should be marked as done.");
    }
}
