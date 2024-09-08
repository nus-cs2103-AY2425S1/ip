package bobbybot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bobbybot.tasks.Task;


public class TaskListTest {

    @Test
    public void testAddTaskAndDeleteTask() {
        TaskList tasks = new TaskList();
        Task task = new TaskStub("test");
        tasks.addTask(task);
        assertEquals("test", tasks.getTask(0).getDescription());
        tasks.deleteTask(0);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void testMarkDoneAndMarkUndone() {
        TaskList tasks = new TaskList();
        Task task = new TaskStub("test");
        tasks.addTask(task);
        tasks.markDone(0);
        assertTrue(tasks.getTask(0).isDone());
        tasks.markUndone(0);
        assertFalse(tasks.getTask(0).isDone());
    }

    @Test
    public void testFindTasks() {
        TaskList tasks = new TaskList();
        Task task1 = new TaskStub("haha test");
        Task task2 = new TaskStub("hehe test");
        tasks.addTask(task1);
        tasks.addTask(task2);
        TaskList tasksFound = tasks.findTasks("haha");
        assertEquals(1, tasksFound.getSize());
        tasksFound = tasks.findTasks("hehe");
        assertEquals(1, tasksFound.getSize());
        tasksFound = tasks.findTasks("test");
        assertEquals(2, tasksFound.getSize());
    }
}
