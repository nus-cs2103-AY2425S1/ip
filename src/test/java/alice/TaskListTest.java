package alice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link TaskList} class.
 * Verifies that the TaskList class correctly handles tasks, including adding and marking tasks.
 */
public class TaskListTest {
    /**
     * Tests the functionality of marking a task as done.
     * <p>
     * This test creates a new {@link Todo} task with a description, adds it to a {@link TaskList},
     * marks the task as done, and then verifies that the task's string representation reflects
     * the updated status (i.e., "[T][X] description").
     * </p>
     */
    @Test
    public void markTaskTest() {
        Todo todo = new Todo("description");
        TaskList list = new TaskList();
        list.addToList(todo);
        list.markTask(1);
        assertEquals(list.getTask().get(0).toString(), "[T][X] description");
    }
}
