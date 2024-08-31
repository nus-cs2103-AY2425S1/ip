package yapper.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoCreation_correctDescription_success() {
        Todo todo = new Todo("Sample Task");
        assertEquals("Sample Task", todo.getDescription());
    }

    @Test
    public void todoMarkAsDone_markedAsDone_success() {
        Todo todo = new Todo("Sample Task");
        todo.markAsDone();
        assertEquals(true, todo.isDone());
    }
}
