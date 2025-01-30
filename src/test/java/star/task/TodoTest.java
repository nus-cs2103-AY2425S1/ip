package star.task;

import org.junit.jupiter.api.Test;
import star.exception.StarException;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo task = new Todo("play game");
        assertEquals("[T][ ] play game", task.toString(), "toString() works!");

        task.markDone();
        assertEquals("[T][X] play game", task.toString(), "markDone() works!");

        String output = task.getOutput();
        assertEquals("T | 1 | play game", output, "getOutput() works!");
    }
}
