package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bobby.exception.EmptyDescriptionException;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void validEventTask() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("Watch Lecture", eventTask.getDescription());
        assertEquals("E", eventTask.getTaskType());
    }

    @Test public void validEventStringFormat() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("[E][ ] Watch Lecture (from: Monday to: Thursday)", eventTask.toString());
    }

    @Test
    public void validDeadlineTaskFormatInFile() throws EmptyDescriptionException {
        String input = "event Watch Lecture /from Monday /to Thursday";
        Event eventTask = Event.createEvent(input);
        assertEquals("E |  | Watch Lecture | /from Monday /to Thursday", eventTask.taskInFile());
    }

    @Test
    public void invalidTodoTask() throws EmptyDescriptionException {
        String input = "event";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> Event.createEvent(input));
    }
}
