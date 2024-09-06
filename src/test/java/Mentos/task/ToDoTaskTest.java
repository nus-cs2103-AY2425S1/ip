package Mentos.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("[T] [ ] Read a book", todo.toString(), "The constructor should format the task correctly.");
    }

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("Read a book");
        todo.markAsDone();
        assertEquals("[T] [X] Read a book", todo.toString(), "The setDone() method should correctly show the task as done.");
    }
}
