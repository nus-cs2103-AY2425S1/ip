package optimus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import optimus.exceptions.OptimusExceptions;
import optimus.tasks.Task;
import optimus.tasks.ToDoTask;


public class TaskListTest {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Task task1 = new ToDoTask("Task 1");
    private final Task task2 = new ToDoTask("Task 2");
    private final Task task3 = new ToDoTask("Task 3");

    private void populateTasks() {
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    @Test
    public void rejectInvalidTaskNumTest() {
        populateTasks();
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.getTask(3);
        } catch (OptimusExceptions e) {
            assertEquals("A task with this number does not exist.", e.getMessage());
        }
    }

    @Test
    public void addTaskTest() {
        populateTasks();
        Task newTask = new ToDoTask("New task");
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.addTask(newTask);
            assertEquals(newTask, taskList.getTask(3));
        } catch (OptimusExceptions e) {
            fail("Task not added correctly");
        }
    }

    @Test
    public void removeTaskTest() {
        populateTasks();
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.removeTask(1);
            assertEquals(task3, taskList.getTask(1));
        } catch (OptimusExceptions e) {
            fail("Task not removed correctly");
        }
    }
}
