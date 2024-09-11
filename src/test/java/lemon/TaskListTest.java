package lemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lemon.exception.DescriptionException;
import lemon.task.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();

        try {
            assertTrue(tl.addNewTask(test));
        } catch (DescriptionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteTaskTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();

        try {
            tl.addNewTask(test);
        } catch (DescriptionException e) {
            throw new RuntimeException(e);
        }


        assertEquals(test, tl.deleteTask(1));
        assertEquals(0, tl.size());
    }

    @Test
    public void findTasksTest() {
        Task test = new TaskStub();
        TaskList tl = new TaskList();
        TaskList found = new TaskList();

        try {
            tl.addNewTask(test);
            found = tl.findTasks("Test");
        } catch (DescriptionException e) {
            throw new RuntimeException(e);
        }

        assertEquals(tl.toString(), found.toString());
    }

}
