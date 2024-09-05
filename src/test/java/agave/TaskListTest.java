package agave;

import agave.Task.Task;
import agave.Task.TaskList;
import agave.Util.AgaveException;
import agave.logic.Deadline;
import agave.logic.Event;
import agave.logic.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList tasks;

    public TaskListTest() {
        tasks = new TaskList();
    }

    @Test
    public void testAddToDo() {
        Task task = new ToDo("task 0");
        tasks.addTask(task);
        assertEquals(1, tasks.getTasks().size());
    }

    @Test
    public void testDeleteTask() throws AgaveException {
        Task task = new ToDo("task 1");
        tasks.addTask(task);
        tasks.deleteTask(1);
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void testMarkTask() throws AgaveException {
        Task task = new ToDo("task 2");
        tasks.addTask(task);
        tasks.markTask(1);
        assertEquals("[X] ", tasks.getTask(0).getStatus());
    }

    @Test
    public void testUnmarkTask() throws AgaveException {
        Task task = new ToDo("task 3");
        tasks.addTask(task);
        tasks.markTask(1);
        tasks.unmarkTask(1);
        assertEquals("[ ] ", tasks.getTask(0).getStatus());
    }

    @Test
    public void testInvalidDeleteTask() {
        Task task = new ToDo("task 4");
        tasks.addTask(task);
        assertThrows(AgaveException.class, () -> tasks.deleteTask(6));
    }

    @Test
    public void testInvalidMarkTask() {
        Task task = new ToDo("task 5");
        tasks.addTask(task);
        assertThrows(AgaveException.class, () -> tasks.markTask(7));
    }

    @Test
    public void testDeadlineTask() {
        Task task = new Deadline("task 6", "2021/09/09 1800");
        tasks.addTask(task);
        assertEquals("task 6", tasks.getTask(0).getDescription());
    }

    @Test
    public void testEventTask() {
        Task task = new Event("task 7", "2021/09/09 1800", "2021/09/09 1900");
        tasks.addTask(task);
        assertEquals("task 7", tasks.getTask(0).getDescription());
    }

}
