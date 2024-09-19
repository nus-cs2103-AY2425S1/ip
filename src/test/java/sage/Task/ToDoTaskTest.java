package sage.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        ToDoTask todo = new ToDoTask("Read a book", "Fun");
        assertEquals("[T][ ] Read a book", todo.toString(), "The constructor should format the task correctly.");
    }

    @Test
    public void testSetDone() {
        ToDoTask todo = new ToDoTask("Read a book", "Fun");
        todo.setDone(true);
        assertEquals("[T][X] Read a book", todo.toString(), "The setDone() method should correctly show the task as done.");
    }

    @Test
    public void testToSave() {
        ToDoTask todo = new ToDoTask("Read a book", "Fun");
        assertEquals("T | 0 | Read a book", todo.toSave(), "The toSave() method should format the task correctly for saving.");
    }
}
