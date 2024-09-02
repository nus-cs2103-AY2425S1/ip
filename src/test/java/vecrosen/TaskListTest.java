package vecrosen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    /**
     * Tests whether TaskList can handle add and delete tasks accurately.
     */
    @Test
    public void addDeleteTaskTest() {
        ArrayList<Task> a = new ArrayList<Task>();
        TaskList taskList = new TaskList(a);
        taskList.addTask(new Task("Hello world"));
        taskList.addTask(new Task("Hello world again"));
        taskList.addTask(new Task("Hello world thrice"));
        taskList.deleteTask(2);
        try {
            taskList.deleteTask(3);
        } catch (IndexOutOfBoundsException ignored) {
            // no action
        }
        assertEquals(2, taskList.getListSize());
        assertEquals("Hello world thrice", a.get(1).getDescription());
    }
}
