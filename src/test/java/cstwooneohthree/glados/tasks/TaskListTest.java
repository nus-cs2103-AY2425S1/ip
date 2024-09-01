package cstwooneohthree.glados.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;

public class TaskListTest {

    @Test
    public void testAddTodo() throws GladosException {
        TaskList taskList = new TaskList(false);
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.add(TaskType.TODO, "test todo"));
    }

    @Test
    public void testAddTodoWithTrailingSpace() throws GladosException {
        TaskList taskList = new TaskList(false);
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.add(TaskType.TODO, "   test todo   "));
    }

    @Test
    public void testAddTodoWithNoDescription() throws GladosException {
        TaskList taskList = new TaskList(false);
        try {
            taskList.add(TaskType.TODO, "");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testAddEvent() throws GladosException {
        TaskList taskList = new TaskList(false);
        assertArrayEquals(
                new String[]{"[E][ ] test event (from: 2025-08-19 to: 2025-08-19)", "1"},
                taskList.add(TaskType.EVENT, "test event /from 2025-08-19 /to 2025-08-19"));
    }

    @Test
    public void testAddEventWithNoFromDate() throws GladosException {
        TaskList taskList = new TaskList(false);
        try {
            taskList.add(TaskType.EVENT, "test event /to 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testAddEventWithNoDateRange() throws GladosException {
        TaskList taskList = new TaskList(false);
        try {
            taskList.add(TaskType.EVENT, "test event");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testAddDeadline() throws GladosException {
        TaskList taskList = new TaskList(false);
        assertArrayEquals(
                new String[]{"[D][ ] test deadline (by: 2025-08-19)", "1"},
                taskList.add(TaskType.DEADLINE, "test deadline /by 2025-08-19"));
    }

    @Test
    public void testAddEventWithNoDescription() throws GladosException {
        TaskList taskList = new TaskList(false);
        try {
            taskList.add(TaskType.EVENT, "/from 2025-08-19 /to 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testAddDeadlineWithNoDescription() throws GladosException {
        TaskList taskList = new TaskList(false);
        try {
            taskList.add(TaskType.DEADLINE, "/by 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }
}

