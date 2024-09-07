package mortalreminder.backend;

import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.ToDoStub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTask_emptyList_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            assertNull(taskList.getTask(0));
            taskList.clearList();
        } catch (Exception e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }

    }

    @Test
    public void getTask_negativeIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            assertNull(taskList.getTask(-1));
            fail();
        } catch (Exception e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }

    }

    @Test
    public void getTask_success() {

        try {
            TaskList taskList = new TaskListStub();
            Task testingTask = new ToDoStub("This is a task.");
            taskList.addTask(testingTask);
            assertEquals(testingTask, taskList.getTask(0));
            taskList.clearList();
        } catch (MortalReminderException e) {
            fail();
        }
    }
}
