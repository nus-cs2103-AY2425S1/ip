package taskList;

import exceptions.GrokInvalidUserInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStub;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static TaskList tasks;

    @BeforeEach
    public void setup() {
        tasks = new TaskList(new ArrayList<>());
    }

    @Test
    public void addTask_singleTask() {
        try {
            assertEquals(tasks.length(), 0);
            Task task = new TaskStub("Hello hello");
            tasks.addTask(task);
            assertEquals(tasks.length(), 1);

            assertSame(tasks.getTaskAt(0), task);
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void addTask_multipleTasks() {
        try {
            assertEquals(tasks.length(), 0);

            Task task = new TaskStub("Hello hello");
            tasks.addTask(task);
            assertSame(tasks.getTaskAt(0), task);
            assertEquals(tasks.length(), 1);

            Task nextTask = new TaskStub("I am another task.");
            tasks.addTask(nextTask);
            assertSame(tasks.getTaskAt(1), nextTask);
            assertEquals(tasks.length(), 2);
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void deleteTaskAt_singleTask() {
        try {
            assertEquals(tasks.length(), 0);
            tasks.addTask(new TaskStub("I am the first task."));
            Task taskToDelete = new TaskStub("I am the second task.");
            tasks.addTask(taskToDelete);
            tasks.addTask(new TaskStub("I am the third task."));

            Task deletedTask = tasks.deleteTaskAt(1);
            assertEquals(tasks.length(), 2);
            assertSame(deletedTask, taskToDelete);
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void getAllTasks_immutableCopyReturned() {
        try {
            Task t1 = new TaskStub("I am the first task.");
            Task t2 = new TaskStub("I am the second task.");
            tasks.addTask(t1);
            tasks.addTask(t2);

            ArrayList<Task> allTasks = tasks.getAllTasks();

            allTasks.remove(1);
            assertEquals(2, tasks.length());
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

}
