package bob.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Todo;

public class ParserTest {
    @Test
    public void validFileFormatForTodo() {
        Todo todo = new Todo("Code assignment");
        String expected = "T | 0 | Code assignment";
        String actual = todo.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void validFileFormatForEvent() {
        Event event = new Event("lunch party", "Mon", "11am", "2pm");
        String expected = "E | 0 | lunch party | Mon | 11am | 2pm";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void validFileFormatForDeadline() {
        Deadline deadline = new Deadline("submission", "22 Sep 2024");
        String expected = "D | 0 | submission | 22 Sep 2024";
        String actual = deadline.getFileFormat();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidFileFormatForTodoBecauseEmptyDescription() {
        Todo todo = new Todo();
        String expected = "T | 0 | ";
        String actual = todo.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForEventBecauseEmptyDescription() {
        Event event = new Event();
        String expected = "E | 0 |  |  |  | ";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForEventBecauseNoDescription() {
        Event event = new Event("", "Mon", "11am", "2pm");
        String expected = "E | 0 |  | Mon | 11am | 2pm";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForEventBecauseNoStartDay() {
        Event event = new Event("lunch party", "", "11am", "2pm");
        String expected = "E | 0 | lunch party |  | 11am | 2pm";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForEventBecauseNoStartTime() {
        Event event = new Event("lunch party", "Mon", "", "2pm");
        String expected = "E | 0 | lunch party | Mon |  | 2pm";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForEventBecauseNoEndTime() {
        Event event = new Event("lunch party", "Mon", "11am", "");
        String expected = "E | 0 | lunch party | Mon | 11am | ";
        String actual = event.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForDeadlineBecauseEmptyDescription() {
        Deadline deadline = new Deadline();
        String expected = "D | 0 |  | ";
        String actual = deadline.getFileFormat();
        assertEquals(expected, actual);
    }
    @Test
    public void invalidFileFormatForDeadlineBecauseNoDueDate() {
        Deadline deadline = new Deadline("submission", "");
        String expected = "D | 0 | submission | ";
        String actual = deadline.getFileFormat();
        assertEquals(expected, actual);
    }
}
