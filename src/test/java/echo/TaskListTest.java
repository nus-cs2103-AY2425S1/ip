package echo;

import echo.task.TaskList;
import echo.task.TaskType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test_delete_task() {
        TaskList taskList = new TaskList();
        taskList.addTask("walk dog", TaskType.TODO, "");
        taskList.addDeadline("catch flight", LocalDate.parse("2024-09-15"));
        taskList.addTask("birthday", TaskType.EVENT, "2pm->3pm");
        taskList.deleteTask(2);

        String expected =
                "1. [T] [ ] walk dog\n" +
                "2. [E] [ ] birthday (from: 2pm to: 3pm)\n";

        assertEquals(
               expected,
               taskList.getTasksString()
        );
    }
}
