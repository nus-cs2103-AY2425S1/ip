package tick.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tick.exceptions.TickException;
import tick.tasks.ToDo;

public class TaskListTest {
    @Test
    public void addTask_correctInput() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void deleteTask_correctInput() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void markTaskAsDone_correctIndex() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTask(0).getStatus());
    }
    @Test
    public void markTaskAsUndone_correctIndex() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskAsDone(0);
        taskList.markTaskAsUndone(0);
        assertFalse(taskList.getTask(0).getStatus());
    }

    @Test
    public void findTasks_correctKeyword() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(1, taskList.findTasks("homework").size());
    }

    @Test
    public void addTask_duplicateTask() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertThrows(TickException.class, () -> taskList.addTask(new ToDo("homework")));
    }

    @Test
    public void deleteTask_invalidIndex() throws TickException {
        TaskList taskList = new TaskList();
        assertThrows(TickException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void markTaskAsDone_invalidIndex() throws TickException {
        TaskList taskList = new TaskList();
        assertThrows(TickException.class, () -> taskList.markTaskAsDone(0));
    }

    @Test
    public void markTaskAsUndone_invalidIndex() throws TickException {
        TaskList taskList = new TaskList();
        assertThrows(TickException.class, () -> taskList.markTaskAsUndone(0));
    }

    @Test
    public void findTasks_noMatchingTasks() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(0, taskList.findTasks("assignment").size());
    }
}
