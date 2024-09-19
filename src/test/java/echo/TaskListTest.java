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

    @Test
    public void test_add_and_mark_tasks() {
        TaskList taskList = new TaskList();

        // Add tasks
        taskList.addTask("go for a run", TaskType.TODO, "");
        taskList.addDeadline("submit assignment", LocalDate.parse("2024-10-16"));
        taskList.addTask("party", TaskType.EVENT, "2pm->3pm");

        // Mark first task as complete
        taskList.markTask(1);

        // Expected output after marking the first task as complete
        String expected =
                "1. [T] [X] go for a run\n" +
                "2. [D] [ ] submit assignment (by: Oct 16 2024)\n" +
                "3. [E] [ ] party (from: 2pm to: 3pm)\n";

        assertEquals(
                expected,
                taskList.getTasksString()
        );
    }
}
