package chatbuddy;

import chatbuddy.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("Read book");
        assertEquals("[T][ ] Read book", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("Finish homework");
        todo.markAsDone();
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testUnmarkAsDone() {
        ToDo todo = new ToDo("Finish homework");
        todo.markAsDone();
        todo.unmarkAsDone();
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}
