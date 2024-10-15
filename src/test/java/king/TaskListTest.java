package king;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import king.task.Todo;
import king.TaskList;

public class TaskListTest {

    @Test
    public void testAddAndRemoveTask() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Write JUnit tests");

        // Add a task
        taskList.add(todo);
        assertEquals(1, taskList.size(), "TaskList should contain 1 task after adding a task.");

        // Remove the task
        taskList.remove(0);
        assertEquals(0, taskList.size(), "TaskList should contain 0 tasks after removing the task.");
    }
}
