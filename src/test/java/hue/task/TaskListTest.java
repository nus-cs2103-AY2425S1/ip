package hue.task;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    private TaskList taskList = new TaskList();


    @Test
    public void testAddTask() {
        Task task = new Todo("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testRemoveTask() {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        taskList.remove(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }
}