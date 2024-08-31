package babblebot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testInitialTaskListIsEmpty() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    void testAddingTaskIncreasesSize() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));  // Verify the task is added correctly
    }

    @Test
    void testDeletingTaskDecreasesSize() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.addTask(task);
        Task retrievedTask = taskList.get(0);
        assertEquals(task, retrievedTask);
    }
}
