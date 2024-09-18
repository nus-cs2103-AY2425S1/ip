package bestie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    void constructor_normalInput_correctDescription() {
        Todo todo = new Todo("Tutorial", Priority.MEDIUM);
        assertEquals(todo.description, "Tutorial");
    }

    @Test
    void constructor_normalInput_correctPriority() {
        Todo highPriorityTodo = new Todo("Tutorial", Priority.HIGH);
        assertEquals(highPriorityTodo.priority, Priority.HIGH);
        Todo mediumPriorityTodo = new Todo("Tutorial", Priority.MEDIUM);
        assertEquals(mediumPriorityTodo.priority, Priority.MEDIUM);
        Todo lowPriorityTodo = new Todo("Tutorial", Priority.LOW);
        assertEquals(lowPriorityTodo.priority, Priority.LOW);
    }
    @Test
    void toSaveFormat_normalInput_savedCorrectly() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        assertEquals("T | 0 | Submit quiz | HIGH", todo.toSaveFormat());
    }

    @Test
    void mark_markTaskCorrectly() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        todo.markTaskDone();
        assertTrue(todo.isDone);
    }

    @Test
    void toSaveFormat_completedTask_savedCorrectly() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        todo.markTaskDone();
        assertEquals("T | 1 | Submit quiz | HIGH", todo.toSaveFormat());
    }

    @Test
    void unmark_unmarkTaskCorrectly() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        todo.markTaskDone();
        todo.markUndone();
        assertFalse(todo.isDone);
    }

    @Test
    void toString_unmarkedTask_correctStringFormat() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        assertEquals("[T][ ] Submit quiz, priority: HIGH", todo.toString());
    }

    @Test
    void toString_markedTask_correctStringFormat() {
        Todo todo = new Todo("Submit quiz", Priority.HIGH);
        todo.markTaskDone();
        assertEquals("[T][X] Submit quiz, priority: HIGH", todo.toString());
    }
}
