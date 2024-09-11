package task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void todo_taskCreation_successful() {
        ToDo todo = new ToDo("CS2103T IP", false);
        assertNotNull(todo);
        assertEquals("CS2103T IP", todo.getTaskName());
        assertFalse(todo.isCompleted());
    }

    private void assertEquals(String cs2103TIp, String taskName) {
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
