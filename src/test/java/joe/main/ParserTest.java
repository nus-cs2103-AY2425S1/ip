package joe.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import joe.utils.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import joe.exceptions.CorruptedFileException;
import joe.exceptions.InvalidCommandException;
import joe.tasks.Deadline;
import joe.tasks.Event;
import joe.tasks.Task;
import joe.tasks.TaskList;
import joe.tasks.ToDo;

/**
 * Tests the Parser class.
 */
public class ParserTest {

    private TaskList tasks;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        parser = new Parser(tasks);
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_mark() throws InvalidCommandException {
        tasks.addTask(new ToDo("read book"));
        parser.parseCommand("mark 1");
        assertTrue(tasks.getTask(0).checkDone());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_unmark() throws InvalidCommandException {
        ToDo todo = new ToDo("read book");
        todo.setDone(true);
        tasks.addTask(todo);
        parser.parseCommand("unmark 1");
        assertFalse(tasks.getTask(0).checkDone());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_delete() throws InvalidCommandException {
        tasks.addTask(new ToDo("read book"));
        parser.parseCommand("delete 1");
        assertEquals(0, tasks.size());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_todo() throws InvalidCommandException {
        parser.parseCommand("todo read book");
        assertEquals(1, tasks.size());
        assertInstanceOf(ToDo.class, tasks.getTask(0));
        assertEquals("read book", tasks.getTask(0).getDescription());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_deadline() throws InvalidCommandException {
        parser.parseCommand("deadline return book | by 12/12/2019 1800");
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
        assertEquals("return book", tasks.getTask(0).getDescription());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_event() throws InvalidCommandException {
        parser.parseCommand("event project meeting | from 12/12/2019 1800 | to 12/12/2019 2100");
        assertEquals(1, tasks.size());
        assertInstanceOf(Event.class, tasks.getTask(0));
        assertEquals("project meeting", tasks.getTask(0).getDescription());
    }

    /**
     * Tests the parseCommand method in Parser.
     * @throws IllegalArgumentException if the date is invalid
     * @throws InvalidCommandException if the command is invalid
     */
    @Test
    public void testParseCommand_query() throws IllegalArgumentException, InvalidCommandException {
        parser.parseCommand("query 12/12/2019");
        // Assuming queryTasksByDate prints the output, we can't assert the output directly.
        // This test ensures no exceptions are thrown.
    }

    /**
     * Tests the parseCommand method in Parser.
     */
    @Test
    public void testParseCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand("invalid command");
        });
    }

    /**
     * Tests the parseTask method in Parser.
     * @throws CorruptedFileException if the file is corrupted
     */
    @Test
    public void testParseTask_todo() throws CorruptedFileException {
        Task task = Parser.parseTask("T | 0 | read book");
        assertInstanceOf(ToDo.class, task);
        assertEquals("read book", task.getDescription());
    }

    /**
     * Tests the parseTask method in Parser.
     * @throws CorruptedFileException if the file is corrupted
     */
    @Test
    public void testParseTask_deadline() throws CorruptedFileException {
        Task task = Parser.parseTask("D | 0 | return book | by 12/12/2019 1800");
        assertInstanceOf(Deadline.class, task);
        assertEquals("return book", task.getDescription());
    }

    /**
     * Tests the parseTask method in Parser.
     * @throws CorruptedFileException if the file is corrupted
     */
    @Test
    public void testParseTask_event() throws CorruptedFileException {
        Task task = Parser.parseTask("E | 0 | project meeting | from 12/12/2019 1800 | to 12/12/2019 2100");
        assertInstanceOf(Event.class, task);
        assertEquals("project meeting", task.getDescription());
    }

    /**
     * Tests the parseTask method in Parser.
     */
    @Test
    public void testParseTask_corrupted() {
        assertThrows(CorruptedFileException.class, () -> {
            Parser.parseTask("<?><?><?><?>");
        });
    }
}
