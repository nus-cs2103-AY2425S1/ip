package tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class TodoTest {

    @Test
    public void createValidTask() {
        Task task = new Todo("Do homework");
        assertEquals("[ ] Do homework ", task.toString());
    }

    @Test
    public void markValidTask() {
        Task task = new Todo("Do homework");
        task.setDone();
        assertEquals("[x] Do homework ", task.toString());
    }

    @Test
    public void getNameTask() {
        Task task = new Todo("Do homework");
        assertEquals("Do homework", task.getName());
    }

    @Test
    public void isDoneTask() {
        Task task = new Todo("Do homework");
        assertFalse(task.isDone());
        task.setDone();
        assertTrue(task.isDone());
        task.setUndone();
        assertFalse(task.isDone());
    }

}
