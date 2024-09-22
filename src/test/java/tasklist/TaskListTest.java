package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.GrokInvalidUserInputException;
import tasks.Task;
import tasks.TaskStub;

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

            ArrayList<Task> tasksToAdd = new ArrayList<>(Arrays.asList(
                    new TaskStub("I am the first task."),
                    new TaskStub("I am the second task."),
                    new TaskStub("I am the third task.")
            ));

            for (int i = 0; i < tasksToAdd.size(); i++) {
                tasks.addTask(tasksToAdd.get(i));

                assertEquals(tasks.length(), i + 1);
                assertSame(tasks.getTaskAt(i), tasksToAdd.get(i));
            }
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void deleteTaskAt_singleTask() {
        try {
            // Check that tasks have been properly cleaned up before continuing
            assertEquals(tasks.length(), 0);

            ArrayList<Task> tasksToAdd = new ArrayList<>(Arrays.asList(
                    new TaskStub("I am the first task."),
                    new TaskStub("I am the second task."),
                    new TaskStub("I am the third task.")
            ));

            for (Task t: tasksToAdd) {
                tasks.addTask(t);
            }

            assertEquals(tasks.length(), 3);

            Task deletedTask = tasks.deleteTaskAt(1);
            assertSame(deletedTask, tasksToAdd.get(1));
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void deleteTaskAt_multipleTasks() {
        try {
            assertEquals(tasks.length(), 0);

            ArrayList<Task> tasksToAdd = new ArrayList<>(Arrays.asList(
                    new TaskStub("I am the first task."),
                    new TaskStub("I am the second task."),
                    new TaskStub("I am the third task.")
            ));

            for (Task t: tasksToAdd) {
                tasks.addTask(t);
            }

            assertEquals(tasks.length(), 3);

            Task deletedFirstTask = tasks.deleteTaskAt(0);
            Task deletedThirdTask = tasks.deleteTaskAt(1);

            assertSame(deletedFirstTask, tasksToAdd.get(0));
            assertSame(deletedThirdTask, tasksToAdd.get(2));
        } catch (GrokInvalidUserInputException e) {
            fail("Something went wrong with the stub.");
        }
    }

    @Test
    public void deleteTaskAt_invalidBoundsOver() {
        try {
            assertEquals(tasks.length(), 0);

            ArrayList<Task> tasksToAdd = new ArrayList<>(Arrays.asList(
                    new TaskStub("I am the first task."),
                    new TaskStub("I am the second task."),
                    new TaskStub("I am the third task.")
            ));

            for (Task t: tasksToAdd) {
                tasks.addTask(t);
            }

            assertEquals(tasks.length(), 3);

            tasks.deleteTaskAt(2);
            tasks.deleteTaskAt(2);
        } catch (GrokInvalidUserInputException | AssertionError e) {
            return;
        }

        // this fail state is placed outside of the try-catch block so that the fail state here
        // is not caught.
        fail("An over-bounds index was accepted without throwing an assertion error or exception.");
    }

    @Test
    public void deleteTaskAt_invalidBoundsUnder() {
        try {
            assertEquals(tasks.length(), 0);

            ArrayList<Task> tasksToAdd = new ArrayList<>(Arrays.asList(
                    new TaskStub("I am the first task."),
                    new TaskStub("I am the second task."),
                    new TaskStub("I am the third task.")
            ));

            for (Task t: tasksToAdd) {
                tasks.addTask(t);
            }

            assertEquals(tasks.length(), 3);

            tasks.deleteTaskAt(-1);
        } catch (GrokInvalidUserInputException | AssertionError e) {
            return;
        }

        // this fail state is placed outside of the try-catch block so that the fail state here
        // is not caught.
        fail("An over-bounds index was accepted without throwing an assertion error or exception.");
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
