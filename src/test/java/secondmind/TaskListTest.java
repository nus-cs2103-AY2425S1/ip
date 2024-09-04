package secondmind;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    void testAddToTaskList_todoCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("todo read book");
        assertEquals(1, taskList.getTaskCount());
        assertTrue(task instanceof ToDoTask);
        assertEquals("read book", task.getDescription());
        assertEquals("T|0|read book", task.getStorageRepresentation());
    }

    @Test
    void testAddToTaskList_deadlineCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("deadline submit report /by 2024-09-01T23:59:59");
        assertEquals(1, taskList.getTaskCount());
        assertTrue(task instanceof DeadlineTask);
        assertEquals("submit report", task.getDescription());
        assertEquals("D|0|submit report|1 Sep 2024 23:59:59 pm", task.getStorageRepresentation());
    }

    @Test
    void testAddToTaskList_eventCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("event school project meeting " +
                "/from 2024-09-10T16:00:00 /to 2024-09-10T18:00:00");
        assertEquals(1, taskList.getTaskCount());
        assertTrue(task instanceof EventTask);
        assertEquals("school project meeting", task.getDescription());
        assertEquals("E|0|school project meeting|10 Sep 2024 16:00:00 pm|10 Sep 2024 18:00:00 pm",
                task.getStorageRepresentation());
    }

    @Test
    void addToTaskList_invalidDateFormat_dateTimeParseExceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertThrows(DateTimeParseException.class, () -> {
            taskList.addToTaskList("deadline submit report /by wrongFormat");
        });
    }

    @Test
    void markAsDone_validTaskNumber_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("todo read book");
        assertEquals("T|0|read book", task.getStorageRepresentation());
        taskList.markAsDone(1);
        assertEquals("T|1|read book", task.getStorageRepresentation());
    }

    @Test
    void markAsDone_invalidTaskNumber_InvalidTaskNumberExceptionThrown() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("todo read book");
        assertEquals("T|0|read book", task.getStorageRepresentation());
        try {
            taskList.markAsDone(5);
        } catch (InvalidTaskNumberException e) {
            assertEquals("Warning! Task number 5 does not exist!", e.toString());
        }
    }

    @Test
    void markAsUndone_validTaskNumber_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = taskList.addToTaskList("todo read book");
        task.markAsDone();
        assertEquals("T|1|read book", task.getStorageRepresentation());
        taskList.markAsUndone(1);
        assertEquals("T|0|read book", task.getStorageRepresentation());
    }

    @Test
    void markAsUndone_invalidTaskNumber_InvalidTaskNumberExceptionThrown() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList.markAsUndone(5);
        } catch (InvalidTaskNumberException e) {
            assertEquals("Warning! Task number 5 does not exist!", e.toString());
        }
    }
}
