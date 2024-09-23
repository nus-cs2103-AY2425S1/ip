package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testInit() {
        Task task = new ToDo("borrow book");
        assertEquals("[T][ ] borrow book tags:", task.toString());
    }

    @Test
    public void testMark() {
        Task task = new ToDo("borrow book");
        task.mark();
        assertEquals("[T][X] borrow book tags:", task.toString());
        task.unmark();
        assertEquals("[T][ ] borrow book tags:", task.toString());
    }

    @Test
    public void testTag() {
        Task task = new ToDo("borrow book");
        task.addTag("#todo");
        assertTrue(task.isTagged("#todo"));
        task.removeTag("#todo");
        assertFalse(task.isTagged("#todo"));
    }
}
