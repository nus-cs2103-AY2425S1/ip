package asta.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import asta.AstaException;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTodoTask() throws AstaException {
        taskList.addTodoTask("Buy groceries");
        assertEquals(1, taskList.getSize());
        assertEquals("Buy groceries", taskList.getTask(0).getDescription());
    }

    @Test
    public void testAddDeadlineTask() throws AstaException {
        taskList.addDeadlineTask("Submit report", "12/09/2024 1800");
        assertEquals(1, taskList.getSize());
        assertEquals("Submit report", taskList.getTask(0).getDescription());
    }

    @Test
    public void testAddEventTask() throws AstaException {
        taskList.addEventTask("Project meeting", "12/09/2024 1400", "12/09/2024 1600");
        assertEquals(1, taskList.getSize());
        assertEquals("Project meeting", taskList.getTask(0).getDescription());
    }

    @Test
    public void testAddRecurringDeadlineTask() throws AstaException {
        taskList.addRecurringDeadlineTask("Weekly report", "12/09/2024 1800", 7);
        assertEquals(1, taskList.getSize());
        assertEquals("Weekly report", taskList.getTask(0).getDescription());
    }

    @Test
    public void testDeleteTask() throws AstaException {
        taskList.addTodoTask("Buy groceries");
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testMarkTask() throws AstaException {
        taskList.addTodoTask("Buy groceries");
        taskList.markTask(0, true);
        assertTrue(taskList.getTask(0).isDone());

        taskList.markTask(0, false);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    public void testGetTaskNumber() throws AstaException {
        taskList.addTodoTask("Buy groceries");
        int taskNumber = taskList.getTaskNumber("mark 1", "mark");
        assertEquals(0, taskNumber);
    }

    @Test
    public void testFindTasks() throws AstaException {
        taskList.addTodoTask("Buy groceries");
        taskList.addTodoTask("Submit report");
        List<Task> foundTasks = taskList.findTasks("report");
        assertEquals(1, foundTasks.size());
        assertEquals("Submit report", foundTasks.get(0).getDescription());
    }

    @Test
    public void testAddDeadlineTaskInvalidDate() {
        AstaException exception = assertThrows(AstaException.class, () -> {
            taskList.addDeadlineTask("Submit report", "invalid date");
        });
        assertEquals("Invalid date format. Please use dd/MM/yyyy HHmm format.", exception.getMessage());
    }

    @Test
    public void testDeleteTaskInvalidNumber() {
        AstaException exception = assertThrows(AstaException.class, () -> {
            taskList.deleteTask(0);
        });
        assertEquals("Unfortunately, the task number provided doesn't seem valid for deletion...",
                exception.getMessage());
    }

    @Test
    public void testAddTodoTaskEmptyDescription() {
        AstaException exception = assertThrows(AstaException.class, () -> {
            taskList.addTodoTask("");
        });
        assertEquals("Unfortunately, Asta can't add a todo without a description...", exception.getMessage());
    }
}
