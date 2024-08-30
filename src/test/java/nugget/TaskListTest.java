package nugget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;
    private Task todoTask;
    private Task deadlineTask;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        todoTask = new Todo("Read book");
        deadlineTask = new Deadline("Submit assignment", "2024-09-01 0900");
    }

    @Test
    void addTask_shouldAddTaskToTaskList() {
        taskList.addTask(todoTask);
        assertEquals(1, taskList.size(), "Task list size should be 1 after adding a task");
        assertEquals(todoTask, taskList.getTask(0), "Added task should be the same as the one retrieved");
    }

    @Test
    void deleteTask_shouldRemoveTaskFromTaskList() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        taskList.deleteTask(0);
        assertEquals(1, taskList.size(), "Task list size should be 1 after deleting a task");
        assertEquals(deadlineTask, taskList.getTask(0), "Remaining task should be the deadline task after deleting the first task");
    }

    @Test
    void markTask_shouldMarkTaskAsCompleted() {
        taskList.addTask(todoTask);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isCompleted(), "Task should be marked as completed");
    }

    @Test
    void unmarkTask_shouldUnmarkTaskAsNotCompleted() {
        taskList.addTask(todoTask);
        taskList.markTask(0); // Mark task first
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).isCompleted(), "Task should be unmarked as not completed");
    }

    @Test
    void size_shouldReturnNumberOfTasks() {
        assertEquals(0, taskList.size(), "Task list size should be 0 initially");
        taskList.addTask(todoTask);
        assertEquals(1, taskList.size(), "Task list size should be 1 after adding a task");
    }

    @Test
    void getTask_shouldReturnCorrectTask() {
        taskList.addTask(todoTask);
        Task retrievedTask = taskList.getTask(0);
        assertEquals(todoTask, retrievedTask, "The retrieved task should match the added task");
    }

    @Test
    void getTasks_shouldReturnAllTasks() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size(), "Should return 2 tasks");
        assertEquals(todoTask, tasks.get(0), "First task should be the todo task");
        assertEquals(deadlineTask, tasks.get(1), "Second task should be the deadline task");
    }
}
