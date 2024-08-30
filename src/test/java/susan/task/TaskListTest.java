package susan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book");
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }
}
