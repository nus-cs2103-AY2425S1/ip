package Joseph.Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
