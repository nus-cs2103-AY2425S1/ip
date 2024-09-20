package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    void execute_addTask_success() {
        TaskList taskList = new TaskList();
        Task task = new Task.Todo("A todo task");
        taskList.add(task);
        assertEquals(task, taskList.get(0), "Stored todo task can be obtained");
    }

    @Test
    void execute_removeTask_success() {
        TaskList taskList = new TaskList();
        Task task = new Task.Todo("A todo task");
        taskList.add(task);
        Task removedTask = taskList.remove(0);
        assertEquals(task, removedTask, "remove() correctly returns previously added task");
        try {
            taskList.get(0);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        System.err.println("taskList is not empty after removal of task");
    }

    @Test
    void execute_size_success() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "New tasklist is empty");
        taskList.add(new Task.Todo("A todo task"));
        taskList.add(new Task.Todo("Another todo task"));
        assertEquals(2, taskList.size(), "Size of taskList should now be 2");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        try {
            taskList.get(0);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        fail("An exception should be thrown for accessing invalid index");
    }
}
