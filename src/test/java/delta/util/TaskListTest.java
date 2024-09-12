package delta.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import delta.task.Task;
import delta.task.Todo;

public class TaskListTest {
    /**
     * Returns list to be used for testing.
     */
    private ArrayList<Task> createTestList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test1"));
        tasks.add(new Todo("test2"));
        tasks.add(new Todo("test3"));
        return tasks;
    }

    @Test
    public void testEquivalentConstructors() {
        assertEquals((new TaskList()).getTasks().toString(), (new TaskList(new ArrayList<>())).getTasks().toString());
    }

    @Test
    public void testGetTasks() {
        TaskList taskList = new TaskList(createTestList());

        assertEquals(createTestList().toString(), taskList.getTasks().toString());
    }

    @Test
    public void testGetTask() {
        TaskList tasklist = new TaskList(createTestList());

        try {
            assertEquals((new Todo("test1")).toString(), tasklist.getTask(0).toString());

            assertEquals((new Todo("test3")).toString(), tasklist.getTask(2).toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetSize() {
        TaskList taskList = new TaskList(createTestList());
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void testAddTask() {
        TaskList tasklist = new TaskList();
        tasklist.addTask(new Todo("test1"));
        tasklist.addTask(new Todo("test2"));
        tasklist.addTask(new Todo("test3"));

        assertEquals(new TaskList(createTestList()).getTasks().toString(), tasklist.getTasks().toString());
    }

    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList(createTestList());
        Todo todo = new Todo("test2");
        todo.markAsDone();

        try {
            assertEquals(todo.toString(), taskList.markTask(2).toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Marking an empty list throws error.
        try {
            TaskList testList = new TaskList();
            testList.markTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! List is empty! Please add tasks!", e.getMessage());
        }

        // Mark task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.markTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }

        // Mark task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.markTask(4);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }

        // Marking a marked task throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.markTask(1);
            testList.markTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Task is already marked as done.", e.getMessage());
        }
    }

    @Test
    public void testUnmarkTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo1 = new Todo("test1");
        Todo todo2 = new Todo("test2");
        Todo todo3 = new Todo("test3");
        todo1.markAsDone();
        todo2.markAsDone();
        todo3.markAsDone();
        tasks.add(todo1);
        tasks.add(todo2);
        tasks.add(todo3);
        TaskList taskList = new TaskList(tasks);

        Todo todo4 = new Todo("test2");
        todo4.markAsDone();
        todo4.markAsNotDone();

        try {
            assertEquals(todo4.toString(), taskList.unmarkTask(2).toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Unmarking an empty list throws error.
        try {
            TaskList testList = new TaskList();
            testList.unmarkTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! List is empty! Please add tasks!", e.getMessage());
        }

        // Unmark task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.unmarkTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }

        // Unmark task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.unmarkTask(4);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }

        // Unmarking an unmarked task throws error.
        try {
            TaskList testList = new TaskList(tasks);
            testList.unmarkTask(1);
            testList.unmarkTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Task is already marked as not done yet.", e.getMessage());
        }
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(createTestList());
        Todo todo = new Todo("test2");

        try {
            assertEquals(todo.toString(), taskList.deleteTask(2).toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Deleting an empty list throws error.
        try {
            TaskList testList = new TaskList();
            testList.deleteTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! List is empty! Please add tasks!", e.getMessage());
        }

        // Delete task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.deleteTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }

        // Delete task index out of range throws error.
        try {
            TaskList testList = new TaskList(createTestList());
            testList.deleteTask(4);
            fail();
        } catch (Exception e) {
            assertEquals("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""", e.getMessage());
        }
    }
}
