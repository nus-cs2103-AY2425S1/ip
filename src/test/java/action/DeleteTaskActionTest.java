package action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.TaskIndexOutOfRangeException;
import task.TaskList;
import task.Todo;

public class DeleteTaskActionTest {
    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        try {
            new DeleteTaskAction(1).execute(new TaskList());
            fail();
        } catch (TaskIndexOutOfRangeException e) {
            assertEquals(e.getMessage(), "Index 1 is out of range!\nYou have no tasks currently.");
        }
    }

    @Test
    public void execute_deleteOnlyTask_success() throws TaskIndexOutOfRangeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("description"));
        new DeleteTaskAction(1).execute(taskList);
        assertEquals(0, taskList.getTaskCount());
        assertEquals("You have no tasks currently.\n", taskList.toString());
    }

    @Test
    public void execute_deleteMiddleTask_success() throws TaskIndexOutOfRangeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("first"));
        taskList.addTask(new Todo("second"));
        taskList.addTask(new Todo("third"));
        new DeleteTaskAction(2).execute(taskList);
        assertEquals(2, taskList.getTaskCount());
        assertEquals("You have 2 tasks currently.\n1. [T][ ] first\n2. [T][ ] third\n", taskList.toString());
    }
}
