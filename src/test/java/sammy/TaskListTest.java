package sammy;

import org.junit.jupiter.api.Test;
import sammy.task.Task;
import sammy.task.TaskList;
import sammy.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testAddAndRetrieveTask() {
        TaskList tasks = new TaskList();

        // Create and add a new task
        Task task = new Todo("Test Task");
        tasks.add(task);

        // Check if the task is added correctly
        assertEquals(1, tasks.size(), "TaskList should have one task.");
        assertEquals(task, tasks.get(0), "The task retrieved should be the same as the one added.");
    }

    @Test
    void testRemoveTask() {
        TaskList tasks = new TaskList();

        // Add a new task and then remove it
        Task task = new Todo("Test Task");
        tasks.add(task);
        Task removedTask = tasks.remove(0);

        // Ensure the task was removed and the TaskList is empty
        assertEquals(task, removedTask, "The removed task should match the one that was added.");
        assertEquals(0, tasks.size(), "TaskList should be empty after removing the task.");
    }

    @Test
    void testMarkTaskAsDone() {
        TaskList tasks = new TaskList();

        // Add a new task
        Task task = new Todo("Test Task");
        tasks.add(task);

        // Mark the task as done
        tasks.get(0).markAsDone();

        // Verify the task is marked as done
        assertTrue(tasks.get(0).isDone(), "The task should be marked as done.");
        assertEquals("[T][X] Test Task", tasks.get(0).toString(), "Task's toString() should reflect it's done.");
    }

    @Test
    void testMarkTaskAsNotDone() {
        TaskList tasks = new TaskList();

        // Add a new task and mark it as done, then mark it as not done
        Task task = new Todo("Test Task");
        tasks.add(task);
        tasks.get(0).markAsDone();  // First mark as done
        tasks.get(0).markAsNotDone();  // Then mark as not done

        // Verify the task is not done
        assertFalse(tasks.get(0).isDone(), "The task should be marked as not done.");
        assertEquals("[T][ ] Test Task", tasks.get(0).toString(), "Task's toString() should reflect it's not done.");
    }
}
