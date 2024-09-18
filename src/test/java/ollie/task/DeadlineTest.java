package ollie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ollie.exception.OllieException;

/**
 * Tests for the {@link Deadline} class.
 */
public class DeadlineTest {

    private Deadline deadlineTask;

    /**
     * Initializes a {@link Deadline} task instance for testing.
     */
    @BeforeEach
    public void setUp() {
        LocalDateTime deadline = LocalDateTime.of(2023, 8, 31, 23, 59);
        deadlineTask = new Deadline("Submit assignment", deadline);
    }

    /**
     * Tests the {@link Deadline#createTask(String)} method with valid command input.
     * Verifies that a {@link Deadline} task is correctly created.
     */
    @Test
    public void createTask_validCommand_deadlineTaskCreated() throws OllieException {
        String command = "deadline Submit report /by: 2023-09-30 23:59";
        Deadline task = Deadline.createTask(command);
        assertEquals("Submit report", task.getDescription());
        assertEquals(LocalDateTime.of(2023, 9, 30, 23, 59), task.getDeadline());
    }

    @Test
    public void createTask_missingDescription_exceptionThrown() {
        String command = "deadline";
        OllieException exception = assertThrows(OllieException.class, () -> Deadline.createTask(command));
        assertEquals("Please add a name for a deadline task! ☺", exception.getMessage());
    }

    @Test
    public void createTask_missingDeadline_exceptionThrown() {
        String command = "deadline Submit report /by: ";
        OllieException exception = assertThrows(OllieException.class, () -> Deadline.createTask(command));
        assertEquals("Please enter a deadline for the task! ☺", exception.getMessage());
    }

    @Test
    public void createTask_invalidDateFormat_exceptionThrown() {
        String command = "deadline Submit report /by: 2023/09/30 23:59";
        OllieException exception = assertThrows(OllieException.class, () -> Deadline.createTask(command));
        assertEquals("Please enter the date in the format: yyyy-MM-dd HH:mm", exception.getMessage());
    }

    /**
     * Tests the {@link Deadline#saveAsString()} method.
     * Verifies that the task is correctly formatted for saving.
     */
    @Test
    public void saveAsString_validDeadline_correctFormatReturned() {
        String expectedString = "DEADLINE |   | Submit assignment | Aug 31 2023 23:59";
        assertEquals(expectedString, deadlineTask.saveAsString());
    }

    /**
     * Tests the {@link Deadline#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void toString_validDeadline_correctStringRepresentationReturned() {
        String expectedString = "[ ] [DEADLINE] Submit assignment (by: Aug 31 2023 23:59)";
        assertEquals(expectedString, deadlineTask.toString());
    }
}
