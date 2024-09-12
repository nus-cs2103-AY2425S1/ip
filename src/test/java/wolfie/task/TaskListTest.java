package wolfie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new Todo("Test Todo", false);
        tasks.add(task);
        assertEquals(1, tasks.size());
        assertEquals("Test Todo", tasks.get(0).getDescription());
    }
}
