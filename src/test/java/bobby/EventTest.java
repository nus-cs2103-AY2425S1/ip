package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bobby.exception.EmptyDescriptionException;
import bobby.tasks.Event;

/**
 * Tests for {@link Event} class.
 */
public class EventTest {

    /**
     * Tests the {@link Event#createEvent(String)} method with correct input.
     * Validates that a Event task is correctly created.
     */
    @Test
    public void validEventTask() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("Watch Lecture", eventTask.getDescription());
        assertEquals("E", eventTask.getTaskType());
    }

    /**
     * Tests the {@link Event#toString()} method.
     * Validates that a Event task is correctly formatted as a string.
     */
    @Test public void validEventStringFormat() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("[E][ ] Watch Lecture (from: Monday to: Thursday)", eventTask.toString());
    }


    /**
     * Tests {@link Event#taskInFile()} method.
     * Validates that a Event task is correctly formatted in a file.
     */
    @Test
    public void validDeadlineTaskFormatInFile() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("E |  | Watch Lecture | /from Monday /to Thursday", eventTask.taskInFile());
    }


    /**
     * Tests the {@link Event#createEvent(String)} method with incorrect input.
     * Validates that a Event task is not created with empty description.
     */
    @Test
    public void invalidEventTask() throws EmptyDescriptionException {
        String input = "event";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> Event.createEvent(input));
    }
}
