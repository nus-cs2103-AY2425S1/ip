package crack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList taskList = new TaskList();
        Todo task = new Todo("Test task");
        taskList.addTask(task);

        // Check if the task was added successfully
        assertEquals(1, taskList.getSize());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void testRemoveTask() {
        TaskList taskList = new TaskList();
        Todo task = new Todo("Test task");
        taskList.addTask(task);

        Task removedTask = taskList.removeTask(0);

        // Check if the task was removed successfully
        assertEquals(0, taskList.getSize());
        assertEquals(task, removedTask);
    }
}
