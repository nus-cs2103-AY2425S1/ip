package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bobby.exception.EmptyDescriptionException;
import bobby.tasks.Deadline;

/**
 * Tests for {@link Deadline} class.
 */
public class DeadlineTest {

    /**
     * Tests the {@link Deadline#createDeadline(String)} method with correct input.
     * Validates that a Deadline task is correctly created.
     */
    @Test
    public void validDeadlineTask() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("Watch Lecture", deadlineTask.getDescription());
        assertEquals("D", deadlineTask.getTaskType());
    }

    /**
     * Tests the {@link Deadline#toString()} method.
     * Validates that a Deadline task is correctly formatted as a string.
     */
    @Test public void validDeadlineStringFormat() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("[D][ ] Watch Lecture (by: Sep 1 2024)", deadlineTask.toString());
    }

    /**
     * Tests {@link Deadline#taskInFile()} method.
     * Validates that a Deadline task is correctly formatted in a file.
     */
    @Test
    public void validDeadlineTaskFormatInFile() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("D |  | Watch Lecture | /by 2024-09-01", deadlineTask.taskInFile());
    }

    /**
     * Tests the {@link Deadline#createDeadline(String)} method with incorrect input.
     * Validates that a Deadline task is not created with empty description.
     */
    @Test
    public void invalidDeadlineTask() throws EmptyDescriptionException {
        String input = "deadline";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class,
                () -> Deadline.createDeadline(input));
    }
}
