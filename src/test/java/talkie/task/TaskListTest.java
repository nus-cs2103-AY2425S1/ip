package talkie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        ToDo test1 = new ToDo("test");
        taskList.addTask(test1);
        assertEquals(test1, taskList.getTask(1), "`getTask` should return the correct task from the list");
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        ToDo test1 = new ToDo("test");
        ToDo test2 = new ToDo("test2");
        taskList.addTask(test1);
        taskList.addTask(test2);

        assertEquals(test2,
                taskList.deleteTask(2),
                "`deleteTask` should delete and return the deleted task from the list");
    }

    @Test
    public void testIsEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty(), "`isEmpty` should return true if the list is empty");
    }
}
