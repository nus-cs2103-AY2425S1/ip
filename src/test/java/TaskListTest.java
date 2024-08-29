import command.InvalidCommandException;
import org.junit.jupiter.api.Test;
import parser.InvalidDateException;
import parser.InvalidTimeException;
import storage.DataIOException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

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
            taskList.addTask(new Task("test", false));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList("test4.txt");

        try {
            taskList.addTask(new Task("test", false));
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
            taskList.addTask(new ToDoTask("test", false));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        try {
            Task task = taskList.markAsDone(1);
            assertEquals("[T][X] test", task.toString());
        } catch (DataIOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testMarkAsNotDone() {
        TaskList taskList = new TaskList("test6.txt");

        try {
            taskList.addTask(new Task("test", true));
        } catch (DataIOException e) {
            e.printStackTrace();
        }

        try {
            Task task = taskList.markAsNotDone(1);
            assertEquals("[ ] test", task.toString());
        } catch (DataIOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        assertEquals("Now you have 1 tasks in the list.", taskList.getTaskCount());
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList("test7.txt");

        try {
            taskList.addTask(new ToDoTask("test", false));
            taskList.addTask(new DeadlineTask("test", false, "28-07-2024"));
            taskList.addTask(new EventTask("test", false, "28-07-2024", "1400", "1800"));
        } catch (DataIOException | InvalidDateException | InvalidTimeException e) {
            e.printStackTrace();
        }

        assertEquals("""
                1. [T][ ] test
                2. [D][ ] test (by: 28 Jul 2024)
                3. [E][ ] test (from: 28 Jul 2024 02:00 PM to: 06:00 PM)""", taskList.toString());
    }
}
