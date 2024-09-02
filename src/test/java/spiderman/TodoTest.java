package spiderman;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodoTaskCanBeCreated() {
        Todo task = new Todo("Walk the dog");
        assertEquals("Walk the dog", task.getDescription(), "The description should match the input");
    }

    @Test
    public void testMarkingTodoAsDone() {
        Todo task = new Todo("Walk the dog");
        task.markAsDone();
        assertTrue(task.isTaskDone());
    }

    @Test
    public void testMarkingTodoAsNotDone() {
        Todo task = new Todo("Walk the dog");
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isTaskDone());
    }
}