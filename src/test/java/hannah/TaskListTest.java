package hannah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hannah.task.Deadline;
import hannah.task.Task;
import hannah.task.TaskList;
import hannah.task.ToDos;


public class TaskListTest {

    @Test
    public void testAddTask() {
        // Arrange
        TaskList taskList = new TaskList();
        Task todoTask = new ToDos("Finish homework");

        // Act
        taskList.addTask(todoTask);

        // Assert
        assertEquals(1, taskList.size(), "TaskList size should be 1 after adding a task");
        assertEquals(todoTask, taskList.getTask(1), "The task added should be the same as the task retrieved");
    }

    @Test
    public void testAddMultipleTasks() {
        // Arrange
        TaskList taskList = new TaskList();
        Task task1 = new ToDos("Finish homework");
        Task task2 = new Deadline("Submit project");
        task2.setDeadline("2023-10-01");

        // Act
        taskList.addTask(task1);
        taskList.addTask(task2);

        // Assert
        assertEquals(2, taskList.size(), "TaskList size should be 2 after adding two tasks");
        assertEquals(task1, taskList.getTask(1), "First task should match task1");
        assertEquals(task2, taskList.getTask(2), "Second task should match task2");
    }
}
