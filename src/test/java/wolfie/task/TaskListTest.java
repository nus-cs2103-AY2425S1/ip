package wolfie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the TaskList class.
 */
class TaskListTest {
    private TaskList tasks;

    /**
     * Sets up the test environment by initialising a TaskList.
     */
    @BeforeEach
    void setUp() {
        tasks = new TaskList();
    }

    /**
     * Tests the add method in TaskList.
     */
    @Test
    void testAddTask() {
        Task task = new Todo("Test Todo", false);
        tasks.add(task);
        assertEquals(1, tasks.size());
        assertEquals("Test Todo", tasks.get(0).getDescription());
    }
}
