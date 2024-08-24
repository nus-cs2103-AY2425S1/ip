package mortal_reminder.backend;

import mortal_reminder.tasks.Task;
import mortal_reminder.tasks.ToDoStub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskListTest {
    @Test
    public void getTask_emptyList_exceptionHandled() {
        TaskList taskList = new TaskList();
        assertNull(taskList.getTask(0));
        taskList.clearList();
    }

    @Test
    public void getTask_negativeIndex_exceptionHandled() {
        TaskList taskList = new TaskList();
        assertNull(taskList.getTask(-1));
        taskList.clearList();
    }

    @Test
    public void getTask_success() {
        TaskList taskList = new TaskListStub();
        Task testingTask = new ToDoStub("This is a task.");
        taskList.addTask(testingTask);
        assertEquals(testingTask, taskList.getTask(0));
        taskList.clearList();
    }
}
