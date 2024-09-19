package mendel.metacognition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import mendel.discretetask.Deadline;
import mendel.discretetask.Todo;

public class TaskListTest {
    @Test
    public void getTask() {
        TaskList taskList = new TaskList();
        assertEquals("Got it. I've added this task:\n  [T][ ] test\nNow you have 1 tasks in the list.",
                taskList.add(Todo.of("todo test")));
        assertEquals("[T][ ] test", taskList.getTask(0).toString());
        try {
            taskList.getTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
        try {
            taskList.getTask(-1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too small.\nIncrease serial.", e.toString());
        }
        try {
            taskList.getTask(2);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
    }

    @Test
    public void hasTask() {
        TaskList taskList = new TaskList();
        assertFalse(taskList.hasTask(0));
        taskList.add(Todo.of("todo test"));
        assertTrue(taskList.hasTask(0));
        assertFalse(taskList.hasTask(1));
        assertFalse(taskList.hasTask(-2));
    }

    @Test
    public void markTest() {
        TaskList taskList = new TaskList();
        taskList.add(Todo.of("todo test"));
        assertEquals("Nice! I've marked this task as done:\n  [T][X] test",
                taskList.marker(0));
        try {
            taskList.marker(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
        try {
            taskList.marker(-1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too small.\nIncrease serial.", e.toString());
        }
        try {
            taskList.marker(2);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
    }

    @Test
    public void unmarkTest() {
        TaskList taskList = new TaskList();
        taskList.add(Todo.of("todo test"));
        assertEquals("OK, I've marked this task as not done yet:\n  [T][ ] test",
                taskList.unMarker(0));
        try {
            taskList.unMarker(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
        try {
            taskList.unMarker(-1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too small.\nIncrease serial.", e.toString());
        }
        try {
            taskList.unMarker(2);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
    }

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList();
        taskList.add(Todo.of("todo test"));
        assertEquals("Noted. I've removed this task:\n  [T][ ] test\n  Now you have 0 tasks in the list.",
                taskList.delete(0));
        taskList.add(Todo.of("todo test"));
        try {
            taskList.delete(1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
        try {
            taskList.delete(-1);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too small.\nIncrease serial.", e.toString());
        }
        try {
            taskList.delete(2);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Serial is too big.\nDecrease serial.", e.toString());
        }
    }

    @Test
    public void findDescription() {
        TaskList taskList = new TaskList();
        taskList.add(Todo.of("todo test"));
        assertEquals("Here are the matching tasks in your list\n  1.[T][ ] test",
                taskList.findDescription("find tes"));
        assertEquals("Here are the matching tasks in your list",
                taskList.findDescription("find apple"));
    }

    @Test
    public void findDeadline() {
        TaskList taskList = new TaskList();
        taskList.add(Deadline.of("deadline test /by 31 Aug 2030"));
        assertEquals("Here are the tasks with deadlines by Dec 01 2030.\n  1.[D][ ] test (by: Aug 31 2030)"
                + "\nYou have 1 tasks for review",
                taskList.find("remind 01 Dec 2030"));
        assertEquals("Here are the tasks with deadlines by Dec 01 2025.\n  Good job! You have no pending tasks!",
                taskList.find("remind 01 Dec 2025"));
    }
}
