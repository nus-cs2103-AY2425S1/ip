package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void todo_taskCreation_successful() {
        ToDo todo = new ToDo("CS2103T IP", false);
        assertNotNull(todo);
        assertEquals("CS2103T IP", todo.getTaskName());
        assertFalse(todo.isCompleted());
    }

    @Test
    public void getTaskName_correctDescription() {
        ToDo todo = new ToDo("CS2103T IP", false);
        assertEquals("CS2103T IP", todo.getTaskName());
    }

    @Test
    public void isCompleted_variousStates_correctState() {
        ToDo todo = new ToDo("CS2103T IP", false);
        assertFalse(todo.isCompleted());
        todo = new ToDo("CS2103T IP", true);
        assertTrue(todo.isCompleted());
    }

    @Test
    public void markAsCompleted_taskMarkCompleted() {
        ToDo todo = new ToDo("CS2103T IP", false);
        todo.markAsCompleted();
        assertTrue(todo.isCompleted());
    }

    @Test
    public void markAsUncompleted_taskMarkUncompleted() {
        ToDo todo = new ToDo("CS2103T IP", true);
        todo.markAsUncompleted();
        assertFalse(todo.isCompleted());
    }

    @Test
    public void writeToFile_variousStates_correctFormat() {
        ToDo todo = new ToDo("CS2103T IP", false);
        assertEquals("T 1 CS2103T IP", todo.writeToFile());
        todo.markAsCompleted();
        assertEquals("T 0 CS2103T IP", todo.writeToFile());
    }
}
