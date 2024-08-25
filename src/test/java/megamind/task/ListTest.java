package megamind.task;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTest {
    @Test
    public void testToString_empty() {
        List list = new List(new ArrayList<>());
        assertEquals("There are no tasks in your list.", list.toString());
    }

    @Test
    public void testToString_notEmpty() {
        List list = new List(new ArrayList<>());
        Task task = new Task("task");
        for (int i = 0; i < 3; i++) {
            list.add(task);
        }
        assertEquals("1. [❌] task\n2. [❌] task\n3. [❌] task\n", list.toString());
    }

    @Test
    public void testMarkTaskAsDone() {
        List list = new List(new ArrayList<>());
        Task task = new Task("task");
        list.add(task);
        list.markTaskAsDone(0);
        assertEquals("[✔️] task", task.toString());
    }

    @Test
    public void testMarkTaskAsNotDone() {
        List list = new List(new ArrayList<>());
        Task task = new Task("task");
        list.add(task);
        list.markTaskAsDone(0);
        list.markTaskAsNotDone(0);
        assertEquals("[❌] task", task.toString());
    }

    @Test
    public void testAdd() {
        List list = new List(new ArrayList<>());
        Task task = new Task("task");
        list.add(task);
        assertEquals("1. [❌] task\n", list.toString());
    }

    @Test
    public void testDelete() {
        List list = new List(new ArrayList<>());
        Task task = new Task("task");
        list.add(task);
        list.delete(0);
        assertEquals("There are no tasks in your list.", list.toString());
    }
}
