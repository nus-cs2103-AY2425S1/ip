package bangmang.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Test Todo");
        assertEquals("[T][ ] Test Todo", todo.toString());
        todo.markTask();
        assertEquals("[T][X] Test Todo", todo.toString());
    }
}
