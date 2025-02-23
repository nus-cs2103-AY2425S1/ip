package phenex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import phenex.exception.PhenexException;
import phenex.task.Task.TaskType;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests that a PhenexException is thrown for inputting an invalid index when marking a task as completed.
     */
    @Test
    public void markTaskCompleted_invalidIndex_throwsPhenexException() {
        TaskList taskList = new TaskList();
        ToDo toDoTask = new ToDo("task Test", TaskType.OTHERS);
        taskList.addTask(toDoTask);

        PhenexException exception = assertThrows(PhenexException.class, () -> taskList.markTaskCompleted(-1, -1));
        assertEquals("\t Invalid input, no such mission!", exception.getMessage());
    }

    /**
     * Tests that adding a valid task to the TaskList works properly.
     */
    @Test
    public void addTask_validTask_taskAddedSuccessfully() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test", TaskType.OTHERS);
        taskList.addTask(toDo);

        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests that taskList correctly finds a task that is contained.
     */
    @Test
    public void containsTask_validTask_returnsTrue() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test", TaskType.PRODUCTIVE);
        taskList.addTask(toDo);

        assertTrue(taskList.containsTask(toDo));
    }

    /**
     * Tests that taskList correctly detects when a task is not contained.
     */
    @Test
    public void containsTask_nonExistingTask_returnsFalse() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test", TaskType.OTHERS);
        taskList.addTask(toDo);
        ToDo task = new ToDo("Not found", TaskType.RECREATIONAL);

        assertFalse(taskList.containsTask(task));
    }

    /**
     * Tests that marking a task in an empty list throws a PhenexException.
     */
    @Test
    public void markTaskCompleted_emptyList_throwsPhenexException() {
        TaskList taskList = new TaskList();

        PhenexException exception = assertThrows(PhenexException.class, () -> taskList.markTaskCompleted(0, 0));
        assertEquals("\t Invalid input, no such mission!", exception.getMessage());
    }

    /**
     * Tests that marking a task with an out-of-bound index throws a PhenexException.
     */
    @Test
    public void markTaskCompleted_outOfBoundsIndex_throwsPhenexException() {
        TaskList taskList = new TaskList();
        ToDo toDoTask = new ToDo("task Test", TaskType.PRODUCTIVE);
        taskList.addTask(toDoTask);

        // Index is out of bounds because only 1 task is present
        PhenexException exception = assertThrows(PhenexException.class, () -> taskList.markTaskCompleted(10, 10));
        assertEquals("\t Invalid input, no such mission!", exception.getMessage());
    }
}
