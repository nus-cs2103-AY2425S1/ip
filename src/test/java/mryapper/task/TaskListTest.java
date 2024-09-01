package mryapper.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void testString() {
        // Empty task list
        assertEquals("You do not have any tasks!", new TaskList().toString());

        // Task list containing 2 stub tasks
        ArrayList<Task> testList = new ArrayList<>(100);
        Task markedStub = new TaskStub();
        markedStub.markAsDone();
        testList.add(new TaskStub());
        testList.add(markedStub);
        TaskList taskList = new TaskList(testList);
        assertEquals(" 1.[ ] stub\n 2.[X] stub", taskList.toString());
    }

    @Test
    public void testAdd() {
        TaskList taskList = new TaskList();

        // test adding task to taskList
        taskList.add(new TaskStub());
        assertEquals(" 1.[ ] stub", taskList.toString());
    }

    @Test
    public void testRemove_success() {
        ArrayList<Task> testList = new ArrayList<>(100);
        testList.add(new TaskStub());
        TaskList taskList = new TaskList(testList);

        // test removing task from taskList
        taskList.remove(1);
        assertEquals("You do not have any tasks!", taskList.toString());
    }

    @Test
    public void testRemove_exceptionThrown() {
        ArrayList<Task> testList = new ArrayList<>(100);
        testList.add(new TaskStub());
        TaskList taskList = new TaskList(testList);

        // removing task number 0
        try {
            taskList.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 1", e.getMessage());
        }

        // removing task number 2 which does not exist
        try {
            taskList.remove(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    public void testCount() {
        TaskList taskList = new TaskList();

        // No task in list
        assertEquals(0, taskList.count());

        // 2 tasks in list
        ArrayList<Task> testList = new ArrayList<>(100);
        testList.add(new TaskStub());
        testList.add(new TaskStub());
        TaskList taskListWithTasks = new TaskList(testList);
        assertEquals(2, taskListWithTasks.count());
    }

    @Test
    public void testMarkAndUnmark_success() {
        ArrayList<Task> testList = new ArrayList<>(100);
        testList.add(new TaskStub());
        TaskList taskList = new TaskList(testList);

        // Marking
        taskList.mark(1);
        assertEquals(" 1.[X] stub", taskList.toString());

        // Unmarking
        taskList.unmark(1);
        assertEquals(" 1.[ ] stub", taskList.toString());
    }

    @Test
    public void testMarkAndUnmark_exceptionThrown() {
        ArrayList<Task> testList = new ArrayList<>(100);
        testList.add(new TaskStub());
        TaskList taskList = new TaskList(testList);

        // Marking task 0
        try {
            taskList.mark(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 1", e.getMessage());
        }

        // Marking non-existent task
        try {
            taskList.mark(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }

        //  Unmarking task 0
        try {
            taskList.unmark(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 1", e.getMessage());
        }

        //  Unmarking non-existent task
        try {
            taskList.unmark(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
}
