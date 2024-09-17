package slothingwaffler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final TaskList tasks = new TaskList(new StorageStub());

    @Test
    public void testAddTodoTask() throws SlothingWafflerException {
        tasks.addTodoTask(new String[]{"Todo", "sample"});
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] sample", tasks.get(0).toString());
    }

    @Test
    public void testMarkTask() throws SlothingWafflerException {
        tasks.addTodoTask(new String[]{"Todo", "sample"});
        tasks.markTask(1);
        assertEquals("[T][X] sample", tasks.get(0).toString());
    }

    @Test
    public void testDeleteTask() throws SlothingWafflerException {
        tasks.addTodoTask(new String[]{"Todo", "Sample Task"});
        Task task = tasks.get(0);
        tasks.deleteTask(1);
        assertEquals(0, tasks.size());
        assertFalse(tasks.getTasks().contains(task));
    }

    @Test
    public void testPrioritiseTask() throws SlothingWafflerException {
        tasks.addTodoTask(new String[]{"Todo", "Sample Task 1"});
        tasks.addTodoTask(new String[]{"Todo", "Sample Task 2"});
        Task task = tasks.get(1);
        tasks.prioritiseTask(2);
        assertEquals(task, tasks.get(0));
    }

}
