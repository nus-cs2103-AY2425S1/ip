package weeny;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Sample Task");

        taskList.addTask(task);
        List<Task> tasks = taskList.getTasks();

        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals("Sample Task", tasks.get(0).getDescription());
        Assertions.assertFalse(tasks.get(0).isDone);
    }

    @Test
    public void testAddMultipleTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);
        List<Task> tasks = taskList.getTasks();

        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals("Task 1", tasks.get(0).getDescription());
        Assertions.assertEquals("Task 2", tasks.get(1).getDescription());
    }
}
