package asta.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void testMarkTaskAsDone() {
        try {
            taskList.addTodoTask("Read a book");
            taskList.markTask(0, true);

            boolean isDone = taskList.getTask(0).isDone;

            assertTrue(isDone, "Task should be marked as done.");

        } catch (AstaException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testUnmarkTaskAsNotDone() {
        try {
            taskList.addTodoTask("Read a book");
            taskList.markTask(0, true);
            taskList.markTask(0, false);

            boolean isNotDone = !taskList.getTask(0).isDone;

            assertTrue(isNotDone, "Task should be marked as not done.");

        } catch (AstaException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testAddDeadlineTask() {
        try {
            taskList.addDeadlineTask("Finish assignment", "28/08/2024 1800");

            assertEquals(1, taskList.getSize(), "Task list should have one task.");
            assertInstanceOf(Deadline.class, taskList.getTask(0), "Task should be of type Deadline.");
            assertEquals("Finish assignment", taskList.getTask(0).description, "Task description should match.");

        } catch (AstaException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testAddDeadlineTaskInvalidDate() {
        assertThrows(AstaException.class, () -> {
            taskList.addDeadlineTask("Finish assignment", "invalid date");
        }, "Should throw an exception for invalid date format.");
    }

    @Test
    public void testDeleteTask() {
        try {
            taskList.addTodoTask("Complete homework");
            taskList.addTodoTask("Read a book");

            taskList.deleteTask(0);

            assertEquals(1, taskList.getSize(), "Task list should have one task left after deletion.");
            assertEquals("Read a book", taskList.getTask(0).description, "Remaining task should be 'Read a book'.");

        } catch (AstaException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        assertThrows(AstaException.class, () -> {
            taskList.deleteTask(-1);
        }, "Should throw an exception for invalid task index.");
    }
}
