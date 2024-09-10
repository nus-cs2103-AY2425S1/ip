package mahesh.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mahesh.command.AddCommand;
import mahesh.command.Command;
import mahesh.command.DeleteCommand;
import mahesh.command.MarkCommand;
import mahesh.command.PrintCommand;
import mahesh.task.Todo;

public class ParserTest {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        storage = new Storage("test.txt"); // Assuming Storage takes a file path
        parser = new Parser(taskList, storage);
    }

    @Test
    public void testParseListCommand() throws MaheshException {
        Command command = parser.parse("list");
        assertTrue(command instanceof PrintCommand);
    }

    @Test
    public void testParseMarkCommand() throws MaheshException {
        taskList.addToList(new Todo("Sample task"));
        Command command = parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws MaheshException {
        taskList.addToList(new Todo("Sample task"));
        Command command = parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseTodoCommand() throws MaheshException {
        Command command = parser.parse("todo Buy milk");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws MaheshException {
        Command command = parser.parse("deadline Submit report /by 2023-10-10T23:59:00");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseEventCommand() throws MaheshException {
        Command command = parser.parse("event Team meeting /from 2023-10-10T10:00:00 /to 2023-10-10T12:00:00");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseDeleteCommand() throws MaheshException {
        taskList.addToList(new Todo("Sample task"));
        Command command = parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(MaheshException.class, () -> {
            parser.parse("invalid command");
        });
    }
}
