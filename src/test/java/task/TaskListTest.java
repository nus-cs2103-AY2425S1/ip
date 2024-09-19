package task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functionality of the TaskList class
 * Includes tests for adding and deleting tasks
 */

public class TaskListTest {
    private TaskList taskList;

    /**
     * This verifies the addition of a task to the TaskList and whether the list size is
     * updated correctly to reflect the new addition.
     */
    @Test
    public void testAddTask() {
        ArrayList<Task> list = new ArrayList<>();

        taskList = new TaskList(list);
        ToDoTask todo = new ToDoTask("Buy milk");

        taskList.addTask(todo);
        assertEquals(1, taskList.getList().size());
    }

    /**
     * This verifies the deletion of a task from the TaskList and whether the list size is
     * updated correctly to reflect the new deletion.The list is also verified to ensure the correct
     * task is deleted.
     */
    @Test
    public void testDelTask() {
        taskList = new TaskList(new ArrayList<>());

        // Add tasks to the list
        ToDoTask todo1 = new ToDoTask("Buy milk");
        ToDoTask todo2 = new ToDoTask("Write code");

        taskList.addTask(todo1);
        taskList.addTask(todo2);

        // Verify that the list has 3 tasks
        assertEquals(2, taskList.getList().size());

        // Delete the second task (index 1)
        taskList.delTask(1);

        // Verify that the list has 2 tasks
        assertEquals(1, taskList.getList().size());

        // Verify that the remaining tasks are as expected
        assertEquals("Buy milk", taskList.getList().get(0).getDescription());
    }
}
