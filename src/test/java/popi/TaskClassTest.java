package popi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import popi.exception.InvalidTaskNumberException;
import popi.exception.PopiException;
import popi.task.Task;
import popi.task.TaskList;
import popi.task.Todo;

public class TaskClassTest {
    private Task task;
    private TaskList tasklist = new TaskList();

    @Test
    public void testAddTask() throws InvalidTaskNumberException {
        task = new Todo("read book");
        tasklist = new TaskList();
        tasklist.addTask(task);
        assertEquals(tasklist.getTask(1), task);
    }

    @Test
    public void testMarkAsDone() throws PopiException {
        task = new Todo("read book");
        tasklist = new TaskList();
        tasklist.addTask(task);
        tasklist.markTask(1);
        assertEquals(tasklist.getTask(1).getStatusIcon(), "X");
    }

    @Test
    public void testMarkAsUndone() throws PopiException {
        task = new Todo("read book");
        tasklist = new TaskList();
        tasklist.addTask(task);
        tasklist.markTask(1);
        tasklist.unmarkTask(1);
        assertEquals(tasklist.getTask(1).getStatusIcon(), " ");
    }
}
