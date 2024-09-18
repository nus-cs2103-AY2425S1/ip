import Alex.Task.Task;
import Alex.Task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_validTask_success() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.addTask(task);

        // Check if the task was added successfully
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void addTask_nullTask_noAddition() {
        TaskList taskList = new TaskList();

        // Add a null task
        taskList.addTask(null);

        // Check if the task list size is still 0
        assertEquals(0, taskList.size());
    }
}
