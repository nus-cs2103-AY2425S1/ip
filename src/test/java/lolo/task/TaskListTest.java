package lolo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        Task removedTask = taskList.deleteTask(0);
        assertEquals(task, removedTask);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testMarkTaskAsDone() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        taskList.markTaskAsDone(0);
        assertTrue(task.isDone); // Accessing the field directly for testing
    }

    @Test
    public void testMarkTaskAsNotDone() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        task.markAsDone(); // Ensure task is done first
        taskList.addTask(task);
        taskList.markTaskAsNotDone(0);
        assertFalse(task.isDone); // Accessing the field directly for testing
    }

    @Test
    public void testGetTasksOnDate() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("Sample Deadline", LocalDateTime.of(2024, 8, 29, 10, 0));
        Event event = new Event("Sample Event", LocalDateTime.of(2024, 8, 29, 10, 0), LocalDateTime.of(2024, 8, 29, 12, 0));
        taskList.addTask(deadline);
        taskList.addTask(event);

        TaskList tasksOnDate = taskList.getTasksOnDate(LocalDateTime.of(2024, 8, 29, 0, 0));
        assertEquals(2, tasksOnDate.size(), "There should be 2 tasks on the specified date.");
        assertTrue(tasksOnDate.getTasks().contains(deadline), "Deadline should be included in the tasks on date.");
        assertTrue(tasksOnDate.getTasks().contains(event), "Event should be included in the tasks on date.");
    }
}


