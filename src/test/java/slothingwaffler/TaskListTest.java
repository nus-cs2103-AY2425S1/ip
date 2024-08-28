package slothingwaffler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final TaskList tasks = new TaskList(new StorageStub());

    @Test
    public void testAddTodoTask() throws SlothingWafflerException {
        Task task = tasks.addTodoTask(new String[]{"Todo", "sample"});
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] sample", tasks.get(0).toString());
    }

    @Test
    public void testMarkTask() throws SlothingWafflerException {
        Task task = tasks.addTodoTask(new String[]{"Todo", "sample"});
        tasks.markTask(0);
        assertEquals("[T][X] sample", task.toString());
    }

    @Test
    public void testDeleteTask() throws SlothingWafflerException {
        Task task = tasks.addTodoTask(new String[]{"Todo", "Sample Task"});
        tasks.deleteTask(0);
        assertEquals(0, tasks.size());
        assertFalse(tasks.getTasks().contains(task));
    }


}
