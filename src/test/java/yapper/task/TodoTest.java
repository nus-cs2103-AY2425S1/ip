package yapper.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(true, todo.isDone());
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
