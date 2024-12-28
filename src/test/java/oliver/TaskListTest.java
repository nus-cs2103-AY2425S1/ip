package oliver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {

    @Test
    public void testDelete() {
        TaskList list = new TaskList();
        ToDo first = new ToDo("Do this first");
        ToDo second = new ToDo("Do this next");
        ToDo third = new ToDo("Do this last");
        list.add(first);
        list.add(second);
        list.add(third);
        list.delete(2);
        assertEquals(third, list.get(1));
        assertEquals(first, list.get(0));
    }

    @Test
    public void testIsEmpty() {
        TaskList list = new TaskList();
        assertTrue(list.isEmpty());
        list.add(new ToDo("To be done"));
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetSize() {
        TaskList list = new TaskList();
        ToDo first = new ToDo("Do this first");
        ToDo second = new ToDo("Do this next");
        ToDo third = new ToDo("Do this last");
        assertEquals(0, list.getSize());
        list.add(first);
        list.add(second);
        list.add(third);
        assertEquals(3, list.getSize());
    }
}
