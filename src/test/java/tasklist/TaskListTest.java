package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.TaskListException;
import task.ToDo;

public class TaskListTest {

    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("dummy 1"));
        tasks.add(new ToDo("dummy 2"));
        tasks.add(new ToDo("dummy 3"));
        assertEquals(3, tasks.size());
        assertEquals("[T][ ] dummy 1 tags:", tasks.get(0).toString());
        assertEquals("[T][ ] dummy 2 tags:", tasks.get(1).toString());
        assertEquals("[T][ ] dummy 3 tags:", tasks.get(2).toString());
    }

    @Test
    public void testAddAndDelete() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("dummy 1"));
        tasks.add(new ToDo("dummy 2"));
        tasks.add(new ToDo("dummy 3"));
        try {
            tasks.delete(1);
        } catch (TaskListException e) {
            assertEquals(1, 0);
        }
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] dummy 1 tags:", tasks.get(0).toString());
        assertEquals("[T][ ] dummy 3 tags:", tasks.get(1).toString());
    }

    @Test
    public void testFind() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("dummy 1"));
        tasks.add(new ToDo("dummmy 2"));
        tasks.add(new ToDo("dummy 3"));
        TaskList filteredTasks = tasks.find("dummy");
        assertEquals(filteredTasks.size(), 2);
        assertEquals("[T][ ] dummy 1 tags:", filteredTasks.get(0).toString());
        assertEquals("[T][ ] dummy 3 tags:", filteredTasks.get(1).toString());
    }
}
