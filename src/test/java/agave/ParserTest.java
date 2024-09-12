package agave;

import agave.Task.Task;
import agave.Util.AgaveException;
import agave.Util.Parser;
import agave.logic.Event;
import agave.logic.Deadline;
import agave.logic.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testGetCommand() {
        Parser parser = new Parser("todo yeet");
        assertEquals("todo", parser.getCommand());
    }

    @Test
    public void testParseTodo() throws AgaveException {
        Parser parser = new Parser("todo yeet");
        Task task = parser.parseTodo();
        assertEquals("yeet", task.getDescription());
        assertInstanceOf(ToDo.class, task);
    }

    @Test
    public void testParseDeadline() throws AgaveException {
        Parser parser = new Parser("deadline return book /by 2024/12/31 2359");
        Task task = parser.parseDeadline();
        assertEquals("return book", task.getDescription());
        assertInstanceOf(Deadline.class, task);
    }

    @Test
    public void testParseEvent() throws AgaveException {
        Parser parser = new Parser("event project meeting /from 2024/12/31 2349 /to 2024/12/31 2359");
        Task task = parser.parseEvent();
        assertEquals("project meeting", task.getDescription());
        assertInstanceOf(Event.class, task);
    }

    @Test
    public void testGetTaskNumber() throws AgaveException {
        Parser parser = new Parser("delete 1");
        assertEquals(1, parser.getTaskNumber());
    }

    @Test
    public void testParseTodoEmptyDescription() {
        Parser parser = new Parser("todo");
        assertThrows(AgaveException.class, () -> parser.parseTodo());
    }

    @Test
    public void testParseDeadlineEmptyDescription() {
        Parser parser = new Parser("deadline /by 2024/12/31 2359");
        assertThrows(AgaveException.class, () -> parser.parseDeadline());
    }

    @Test
    public void testParseDeadlineEmptyBy() {
        Parser parser = new Parser("deadline return book /by");
        assertThrows(AgaveException.class, () -> parser.parseDeadline());
    }

    @Test
    public void testParseEventEmptyDescription() {
        Parser parser = new Parser("event /from 2024/12/31 2349 /to 2024/12/31 2359");
        assertThrows(AgaveException.class, () -> parser.parseEvent());
    }

    @Test
    public void testParseEventEmptyFrom() {
        Parser parser = new Parser("event project meeting /from /to 2024/12/31 2359");
        assertThrows(AgaveException.class, () -> parser.parseEvent());
    }

}
