package morgana.parser;

import static morgana.common.Messages.MESSAGE_INVALID_TASK_NUMBER;
import static morgana.common.Messages.MESSAGE_MISSING_TASK_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import morgana.exceptions.MorganaException;
import morgana.task.Deadline;
import morgana.task.Event;
import morgana.task.Task;
import morgana.task.Todo;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        MorganaException exception = assertThrows(MorganaException.class, () ->
                Parser.parse("blah"));
        assertEquals("Unknown command: blah", exception.getMessage());
    }

    @Test
    public void parseTaskIndex_missingTaskNumber_exceptionThrown() {
        MorganaException exception = assertThrows(MorganaException.class, () ->
                Parser.parseTaskIndex(""));
        assertEquals(MESSAGE_MISSING_TASK_NUMBER, exception.getMessage());
    }

    @Test
    public void parseTaskIndex_invalidTaskNumber_exceptionThrown() {
        MorganaException exception = assertThrows(MorganaException.class, () ->
                Parser.parseTaskIndex("x"));
        assertEquals(MESSAGE_INVALID_TASK_NUMBER, exception.getMessage());
    }

    @Test
    public void parseTask_validTasks_success() throws MorganaException {
        Task task = Parser.parseTask("T | X | read book");
        assertEquals(Todo.class, task.getClass());

        task = Parser.parseTask("D |   | return book | 2019-10-15 1800");
        assertEquals(Deadline.class, task.getClass());

        task = Parser.parseTask("E |   | project meeting | 2019-10-15 1400 | 2019-10-15 1600");
        assertEquals(Event.class, task.getClass());
    }
}
