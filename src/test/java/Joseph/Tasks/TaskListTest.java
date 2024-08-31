package Joseph.Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("lunch");
        taskList.addTask(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(1));
    }

    @Test
    public void testDeleteTask() {
        TaskList tasklist = new TaskList();
        Task todo = new ToDo("lunch");
        tasklist.addTask(todo);
        Task deletedTask = tasklist.deleteTask(1);

        assertEquals(0, tasklist.size());
        assertEquals(todo, deletedTask);
    }

    @Test
    public void testDeleteTaskFromEmptyList() {
        TaskList tasklist = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tasklist.deleteTask(1));
    }

    @Test
    public void testFindTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Read book"));
        taskList.addTask(new ToDo("Finish Level-9"));
        taskList.addTask(new ToDo("Read another book"));

        ArrayList<Task> matchingTasks = taskList.findTasks("Read");
        assertEquals(2, matchingTasks.size());
        assertEquals("Read book", matchingTasks.get(0).getDesc());
        assertEquals("Read another book", matchingTasks.get(1).getDesc());
    }
}
