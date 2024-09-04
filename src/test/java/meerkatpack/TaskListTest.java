package meerkatpack;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markTodoAsDone_whenCalled_changeDoneStatus() throws IOException {
        TaskList taskList = new TaskList();
        Todo todoTask = new Todo("eat");
        boolean preMarkStatus = todoTask.isCompleted();
        taskList.markTaskAsDone(1);
        boolean postMarkStatus = todoTask.isCompleted();
        assertEquals(preMarkStatus, postMarkStatus);
    }
}
