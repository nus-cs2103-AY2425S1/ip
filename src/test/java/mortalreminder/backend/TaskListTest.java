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
            assertEquals("List is empty!", e.getMessage());
        }

    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            Task testingTask = new ToDoStub("This is a task.");
            taskList.addTask(testingTask);
            assertNull(taskList.getTask(2));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid task number!\nPlease input a number between 1 and 1", e.getMessage());
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
