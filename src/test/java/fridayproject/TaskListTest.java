package fridayproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach  
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Tasks todo = new Todo("Read book");
        taskList.addTask(todo);
        assertEquals(1, taskList.size(), "The size of task list should be 1 after adding a task.");
        assertEquals("Read book", taskList.getTask(0).description, "The task description should match.");
    }

    @Test
    public void testDeleteTask() {
        Tasks todo = new Todo("Read book");
        taskList.addTask(todo);
        Tasks deletedTask = taskList.deleteTask(0);
        assertEquals("Read book", deletedTask.description, "The description of the deleted task should match.");
        assertEquals(0, taskList.size(), "The size of task list should be 0 after deleting a task.");
    }
}

// done with the guidance of ChatGPT