package gutti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Read a book", false);
        Task task2 = new Task("Write a report", false);

        taskList.addTask(task1);
        taskList.addTask(task2);

        // Verify that the tasks were added correctly
        assertEquals(2, taskList.getTasks().size());
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task2, taskList.getTasks().get(1));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Read a book", false);

        taskList.addTask(task);
        taskList.deleteTask(0);

        // Verify that the task was deleted correctly
        assertEquals(0, taskList.getTasks().size());
    }
}
