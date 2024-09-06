package meerkatpack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import taskpack.TaskList;

public class TaskListTest {
    @Test
    public void setMostRecentTaskCompletionStatus_whenCalled_setDoneStatus() throws IOException {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("eat", true);
        boolean preMarkStatus = taskList.getTaskList().get(taskList.getSize() - 1).isCompleted();
        taskList.setMostRecentTaskCompletionStatus(true);
        boolean postMarkStatus = taskList.getTaskList().get(taskList.getSize() - 1).isCompleted();
        assertEquals(preMarkStatus, !postMarkStatus);
    }
}
