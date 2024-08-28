package joe.Main;

import joe.exceptions.CorruptedFileException;
import joe.exceptions.InvalidCommandException;
import joe.tasks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private TaskList tasks;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        parser = new Parser(tasks);
    }

    @Test
    public void testParseCommand_mark() throws InvalidCommandException {
        tasks.addTask(new ToDo("read book"));
        parser.parseCommand("mark 1");
        assertTrue(tasks.get(0).isDone());
    }

    @Test
    public void testParseCommand_unmark() throws InvalidCommandException {
        ToDo todo = new ToDo("read book");
        todo.setDone(true);
        tasks.addTask(todo);
        parser.parseCommand("unmark 1");
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    public void testParseCommand_delete() throws InvalidCommandException {
        tasks.addTask(new ToDo("read book"));
        parser.parseCommand("delete 1");
        assertEquals(0, tasks.size());
    }

    @Test
    public void testParseCommand_todo() throws InvalidCommandException {
        parser.parseCommand("todo read book");
        assertEquals(1, tasks.size());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertEquals("read book", tasks.get(0).getDescription());
    }

    @Test
    public void testParseCommand_deadline() throws InvalidCommandException {
        parser.parseCommand("deadline return book | by 12/12/2019 1800");
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertEquals("return book", tasks.get(0).getDescription());
    }

    @Test
    public void testParseCommand_event() throws InvalidCommandException {
        parser.parseCommand("event project meeting | from 12/12/2019 1800 | to 12/12/2019 2100");
        assertEquals(1, tasks.size());
        assertInstanceOf(Event.class, tasks.get(0));
        assertEquals("project meeting", tasks.get(0).getDescription());
    }

    @Test
    public void testParseCommand_query() throws IllegalArgumentException, InvalidCommandException {
        parser.parseCommand("query 12/12/2019");
        // Assuming queryTasksByDate prints the output, we can't assert the output directly.
        // This test ensures no exceptions are thrown.
    }

    @Test
    public void testParseCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand("invalid command");
        });
    }

    @Test
    public void testParseTask_todo() throws CorruptedFileException {
        Task task = Parser.parseTask("T | 0 | read book");
        assertInstanceOf(ToDo.class, task);
        assertEquals("read book", task.getDescription());
    }

    @Test
    public void testParseTask_deadline() throws CorruptedFileException {
        Task task = Parser.parseTask("D | 0 | return book | by 12/12/2019 1800");
        assertInstanceOf(Deadline.class, task);
        assertEquals("return book", task.getDescription());
    }

    @Test
    public void testParseTask_event() throws CorruptedFileException {
        Task task = Parser.parseTask("E | 0 | project meeting | from 12/12/2019 1800 | to 12/12/2019 2100");
        assertInstanceOf(Event.class, task);
        assertEquals("project meeting", task.getDescription());
    }

    @Test
    public void testParseTask_corrupted() {
        assertThrows(CorruptedFileException.class, () -> {
            Parser.parseTask("<?><?><?><?>");
        });
    }
}
