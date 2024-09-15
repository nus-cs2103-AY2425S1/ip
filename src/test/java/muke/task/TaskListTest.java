package muke.task;

import org.junit.jupiter.api.Test;

import muke.task.Task;
import muke.task.TaskList;
import muke.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

public class TaskListTest {

    @Test
    public void addTask_addsTaskSuccessfully() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        
        taskList.addTask(task);
        
        assertEquals(1, taskList.getSize());
        assertEquals("Test task", taskList.getTask(0).getDescription());
    }

    @Test
    public void findTasksByKeyword_findsMatchingTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Buy milk");
        Task task2 = new ToDo("Read book");
        Task task3 = new ToDo("Buy groceries");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        List<Task> matchingTasks = taskList.findTasksByKeyword("Buy");

        assertEquals(2, matchingTasks.size());
        assertEquals("Buy milk", matchingTasks.get(0).getDescription());
        assertEquals("Buy groceries", matchingTasks.get(1).getDescription());
    }

    @Test
    public void deleteTask_deletesTaskSuccessfully() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Task 1");
        Task task2 = new ToDo("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);

        // Ensure the tasks are added
        assertEquals(2, taskList.getSize());

        // Delete the first task
        taskList.deleteTask(0);

        // Check if the size of the list is now 1
        assertEquals(1, taskList.getSize());

        // Ensure the remaining task is the second one
        assertEquals("Task 2", taskList.getTask(0).getDescription());
    }

    @Test
    public void deleteTask_throwsExceptionWhenDeletingNonExistentTask() {
        TaskList taskList = new TaskList();

        // Trying to delete a task from an empty list should throw an IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }
}