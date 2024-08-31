package nuffle;

import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    void addTask_taskIsAddedSuccessfully() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);

        // Ensure the task is added to the list
        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Test task", taskList.getTask(0).toString());
    }

}
