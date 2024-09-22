package fred;

import fred.Tasks.Deadline;
import fred.Tasks.Event;
import fred.Tasks.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void createTask_todoTask_success() throws Exception {
        TaskType taskType = TaskType.TODO;
        String taskDetails = "return book";
        Todo expected = new Todo("return book");
        Todo actual = (Todo) new TaskList().createTask(taskType, taskDetails);
        assertEquals(compareTodo(expected, actual), true);
    }

    @Test
    public void createTask_deadlineTask_success() throws Exception {
        TaskType taskType = TaskType.DEADLINE;
        String taskDetails = "return book /by 2024-01-01 08:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime by = LocalDateTime.parse("2024-01-01 08:00", formatter);
        Deadline expected = new Deadline("return book", by);
        Deadline actual = (Deadline) new TaskList().createTask(taskType, taskDetails);
        assertEquals(compareDeadline(expected, actual), true);
    }

    @Test
    public void createTask_eventTask_success() throws Exception {
        TaskType taskType = TaskType.EVENT;
        String taskDetails = "orientation week /from 2024-02-02 13:00 /to 2024-02-03 14:20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2024-02-02 13:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2024-02-03 14:20", formatter);
        Event expected = new Event("orientation week", from, to);
        Event actual = (Event) new TaskList().createTask(taskType, taskDetails);
        assertEquals(compareEvent(expected, actual), true);
    }

    @Test
    public void createTask_invalidDeadline_exceptionThrown() {
        try {
            TaskType taskType = TaskType.DEADLINE;
            String taskDetails = "return book /by Sunday";
            new TaskList().createTask(taskType, taskDetails);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Please use this format: deadline return book /by 2000-01-01 00:00", e.getMessage());
        }
    }

    @Test
    public void createTask_invalidEvent_exceptionThrown() {
        try {
            TaskType taskType = TaskType.EVENT;
            String taskDetails = "event orientation week /from Friday /to Tuesday";
            new TaskList().createTask(taskType, taskDetails);
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Please use this format: event orientation day /from 2000-01-01 00:00 /to 2000-01-02 00:00", e.getMessage());
        }
    }

    public boolean compareTodo(Todo todo1, Todo todo2) {
        if (!todo1.toString().equals(todo2.toString())) {
            return false;
        }
        return true;
    }

    public boolean compareDeadline(Deadline deadline1, Deadline deadline2) {
        if (!deadline1.toString().equals(deadline2.toString())) {
            return false;
        }
        return true;
    }

    public boolean compareEvent(Event event1, Event event2) {
        if (!event1.toString().equals(event2.toString())) {
            return false;
        }
        return true;
    }
}
