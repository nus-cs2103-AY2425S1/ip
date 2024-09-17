package lemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lemon.task.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();

        assertTrue(tl.addNewTask(test));
    }

    @Test
    public void deleteTaskTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();

        tl.addNewTask(test);

        assertEquals(test, tl.deleteTask(1));
        assertEquals(0, tl.size());
    }

    @Test
    public void findTasksTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();
        TaskList found = new TaskList();

        tl.addNewTask(test);
        found = tl.findTasks("Test");

        assertEquals(tl.toString(), found.toString());
    }

}
