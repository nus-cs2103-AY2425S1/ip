package jeriel.task;

import jeriel.util.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_validTask_success() {
        TaskList tasks = new TaskList();
        Task task = new Todo("New Task");
        tasks.addTask(task);

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void deleteTask_existingTask_success() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.deleteTask(0);

        assertEquals(1, tasks.size());
        assertEquals(task2, tasks.get(0));
    }

    @Test
    public void getTask_validIndex_success() {
        TaskList tasks = new TaskList();
        Task task = new Todo("Sample Task");
        tasks.addTask(task);

        assertEquals(task, tasks.get(0));
    }
}
