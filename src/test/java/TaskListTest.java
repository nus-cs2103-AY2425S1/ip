import Mario.TaskList;
import Mario.Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testAddTask() {
        Task task = new Task("Task name");
        TaskList list = new TaskList();
        list.addTask(task);
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(task);
        assertEquals(expectedList, list.getAllTasks());

    }

    @Test
    public void testGetTask() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        TaskList list = new TaskList((ArrayList<Task>) tasks);

        assertEquals(list.getTask(1), task1);
        assertEquals(list.getTask(2), task2);
        assertEquals(list.getTask(3), task3);

    }

    @Test
    public void testMarkCompleted() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        TaskList list = new TaskList((ArrayList<Task>) tasks);

        list.markCompleted(1);
        assertTrue(task1.getIsCompleted());

        list.markCompleted(2);
        assertTrue(task2.getIsCompleted());

        list.markCompleted(3);
        assertTrue(task3.getIsCompleted());

    }

    @Test
    public void testMarkUncompleted() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        TaskList list = new TaskList((ArrayList<Task>) tasks);

        list.markUncompleted(1);
        assertFalse(task1.getIsCompleted());

        list.markUncompleted(2);
        assertFalse(task2.getIsCompleted());

        list.markUncompleted(3);
        assertFalse(task3.getIsCompleted());

    }
}
