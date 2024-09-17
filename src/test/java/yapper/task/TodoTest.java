package yapper.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todoCreation_correctDescription_success() {
        Todo todo = new Todo("Finish assignment");
        assertEquals("Finish assignment", todo.getDescription());
    }

    @Test
    public void todoMarkAsDone_markedAsDone_success() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        assertTrue(todo.isDone(), "Todo should be marked as done");
    }

    @Test
    public void todoToString_notDone_correctString() {
        Todo todo = new Todo("Finish cs2103t assignment");
        String expectedOutput = "[T][ ] Finish cs2103t assignment";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void todoToString_done_correctString() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        String expectedOutput = "[T][X] Finish assignment";
        assertEquals(expectedOutput, todo.toString());
    }
}
