package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testIsEmptyInitially() {
        assertTrue(taskList.isEmpty(), "Task list should be empty initially");
    }

    @Test
    void testAddTodo() {
        taskList.addTodo("Read a book");
        assertEquals(1, taskList.getSize(), "Task list should have one task after adding a Todo");
        assertFalse(taskList.isEmpty(), "Task list should not be empty after adding a task");
        assertTrue(taskList.getTask(0) instanceof Todo, "The task should be an instance of Todo");
        assertEquals("Read a book", taskList.getTask(0).getName(), "The task description should match");
    }

    @Test
    void testAddDeadline() {
        LocalDateTime dueDate = LocalDateTime.of(2024, 8, 30, 18, 0);
        taskList.addDeadline("Submit report", dueDate);
        assertEquals(1, taskList.getSize(), "Task list should have one task after adding a Deadline");
        assertFalse(taskList.isEmpty(), "Task list should not be empty after adding a task");
        assertTrue(taskList.getTask(0) instanceof Deadline, "The task should be an instance of Deadline");
        assertEquals("Submit report", taskList.getTask(0).getName(), "The task description should match");
        assertEquals(dueDate, ((Deadline) taskList.getTask(0)).getTime(), "The due date should match");
    }

    @Test
    void testAddEvent() {
        LocalDateTime startTime = LocalDateTime.of(2024, 8, 30, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 8, 30, 11, 0);
        taskList.addEvent("Team meeting", startTime, endTime);
        assertEquals(1, taskList.getSize(), "Task list should have one task after adding an Event");
        assertFalse(taskList.isEmpty(), "Task list should not be empty after adding a task");
        assertTrue(taskList.getTask(0) instanceof Event, "The task should be an instance of Event");
        assertEquals("Team meeting", taskList.getTask(0).getName(), "The task description should match");
        assertEquals(startTime, ((Event) taskList.getTask(0)).getStartTime(), "The start time should match");
        assertEquals(endTime, ((Event) taskList.getTask(0)).getEndTime(), "The end time should match");
    }

    @Test
    void testCompleteTaskInList() {
        taskList.addTodo("Read a book");
        taskList.completeTaskInList(1);
        assertTrue(taskList.getTask(0).isComplete(), "Task should be marked as completed");
    }

    @Test
    void testUnmarkTaskInList() {
        taskList.addTodo("Read a book");
        taskList.completeTaskInList(1);
        taskList.unmarkTaskInList(1);
        assertFalse(taskList.getTask(0).isComplete(), "Task should be unmarked as completed");
    }

    @Test
    void testDeleteTaskInList() {
        taskList.addTodo("Read a book");
        Task deletedTask = taskList.deleteTaskInList(1);
        assertEquals("Read a book", deletedTask.getName(), "Deleted task description should match");
        assertEquals(0, taskList.getSize(), "Task list should be empty after deleting the task");
    }

}
