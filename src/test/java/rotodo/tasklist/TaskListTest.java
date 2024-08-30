package rotodo.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    interface TaskInfo {}
    public class TodoInfo {
        String value;

    }
    public class DeadlineInfo implements TaskInfo {
        String value;
        LocalDateTime by;
    }
    public class EventInfo implements TaskInfo {
        String value;
        LocalDateTime from;
        LocalDateTime to;
    }

    @Test
    public void add_tasks_populatesListWithTasks() {
        // Arrange
        TaskList tasklist = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date1 = LocalDateTime.parse("01/01/2001 0102", formatter);
        LocalDateTime date2 = LocalDateTime.parse("02/02/2003 0203", formatter);
        String expected = "1.[T][ ] Todo task\n"
            + "2.[D][ ] Deadline task (by: 01/01/2001 0102)\n"
            + "3.[E][ ] Event task (from: 01/01/2001 0102 to: 02/02/2003 0203)";

        // Act
        tasklist.addTask("Todo task");
        tasklist.addTask("Deadline task", date1);
        tasklist.addTask("Event task", date1, date2);

        // Assert
        String actual = tasklist.toString();
        assertEquals(expected, actual, "TaskList printed is wrong");
    }

    @Test
    public void mark_taskAsDone_setTaskToDone() {
        // Arrange
        TaskList tasklist = new TaskList();
        tasklist.addTask("dummy task");
        String expected = "1.[T][X] dummy task";

        // Act
        try {
            tasklist.markDone(0);
        } catch (Exception e) {
            // should not have exception here
        }

        // Assert
        String actual = tasklist.toString();
        assertEquals(expected, actual,
            String.format("Task should be marked as done, but is not."));
    }

    @Test
    public void unmark_taskAsUndone_setTaskToUndone() {
        // Arrange
        TaskList tasklist = new TaskList();
        tasklist.setNextStatus(true);
        tasklist.addTask("dummy task");
        String expected = "1.[T][ ] dummy task";

        // Act
        try {
            tasklist.unmarkDone(0);
        } catch (Exception e) {
            ;
        }

        // Assert
        String actual = tasklist.toString();
        assertEquals(expected, actual,
            String.format("Task should be marked as undone, but is not."));
    }
}
