package JayTest;

import jay.TaskList;
import jay.command.InvalidCommandException;
import org.junit.jupiter.api.Test;
import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;
import jay.storage.DataIOException;
import jay.task.DeadlineTask;
import jay.task.EventTask;
import jay.task.Task;
import jay.task.ToDoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void testLoadTask() {
        TaskList taskList = new TaskList("");

        assertEquals("Now you have 0 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testGetTaskCount() {
        TaskList taskList = new TaskList("test1.txt");

        assertEquals("Now you have 0 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testIsEmpty() {
        TaskList taskList = new TaskList("test2.txt");

        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList("test3.txt");

        try {
            taskList.addTask(new Task("test", false, Task.Priority.Low));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList("test4.txt");

        try {
            taskList.addTask(new Task("test", false, Task.Priority.Low));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        try {
            taskList.removeTask(1);
        } catch (DataIOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 0 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testMarkAsDone() {
        TaskList taskList = new TaskList("test5.txt");

        try {
            taskList.addTask(new ToDoTask("test", false, Task.Priority.Low));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        try {
            Task task = taskList.markAsDone(1);
            assertEquals("[T][X] test { Priority: Low }", task.toString());
        } catch (DataIOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testMarkAsNotDone() {
        TaskList taskList = new TaskList("test6.txt");

        try {
            taskList.addTask(new Task("test", true, Task.Priority.Low));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        try {
            Task task = taskList.markAsNotDone(1);
            assertEquals("[ ] test { Priority: Low }", task.toString());
        } catch (DataIOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList("test7.txt");

        try {
            taskList.addTask(new ToDoTask("test", false, Task.Priority.Low));
            taskList.addTask(new DeadlineTask("test", false, Task.Priority.Low, "28-07-2024"));
            taskList.addTask(new EventTask("test", false, Task.Priority.Low,
                    "28-07-2024", "1400", "1800"));
        } catch (DataIOException | InvalidDateException | InvalidTimeException e) {
            e.printStackTrace();
        }

        assertEquals("""
                1. [T][ ] test { Priority: Low }
                2. [D][ ] test { Priority: Low } (by: 28 Jul 2024)
                3. [E][ ] test { Priority: Low } (from: 28 Jul 2024 02:00 PM to: 06:00 PM)""", taskList.toString());
    }

    @Test
    public void testFindTasks() {
        TaskList taskList = new TaskList("test8.txt");

        try {
            taskList.addTask(new ToDoTask("test", false, Task.Priority.Low));
            taskList.addTask(new DeadlineTask("test", false, Task.Priority.Low, "28-07-2024"));
            taskList.addTask(new EventTask("test", false, Task.Priority.Low,
                    "28-07-2024", "1400",
                    "1800"));
        } catch (DataIOException | InvalidDateException | InvalidTimeException e) {
            e.printStackTrace();
        }

        assertEquals("""
                1. [T][ ] test { Priority: Low }
                2. [D][ ] test { Priority: Low } (by: 28 Jul 2024)
                3. [E][ ] test { Priority: Low } (from: 28 Jul 2024 02:00 PM to: 06:00 PM)""",
                taskList.findTasks("test").toString());
    }
}
