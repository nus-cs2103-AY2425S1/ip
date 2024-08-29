package bobby.parser;

import bobby.command.Command;
import bobby.exceptions.*;
import bobby.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParser {
    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void testParseCommand_validCommands() {
        assertEquals(Command.BYE, parser.parseCommand("bye"));
        assertEquals(Command.LIST, parser.parseCommand("list"));
        assertEquals(Command.MARK, parser.parseCommand("mark 1"));
        assertEquals(Command.UNMARK, parser.parseCommand("unmark 1"));
        assertEquals(Command.DELETE, parser.parseCommand("delete 1"));
        assertEquals(Command.TODO, parser.parseCommand("todo read book"));
        assertEquals(Command.DEADLINE, parser.parseCommand("deadline return book /by 2024-12-12"));
        assertEquals(Command.EVENT, parser.parseCommand("event meeting /from 2024-12-12 /to 2024-12-13"));
        assertEquals(Command.FIND, parser.parseCommand("find 2024-12-12"));
    }

    @Test
    void testParseCommand_invalidCommand() {
        assertEquals(Command.UNKNOWN, parser.parseCommand("SomethingReallyRandomLOL"));
    }

    @Test
    void testParseTask_validTodo() throws BobbyException {
        Task task = parser.parseTask("todo read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    void testParseTask_validDeadline() throws BobbyException {
        Task task = parser.parseTask("deadline return book /by 2024-12-12");
        assertEquals("[D][ ] return book (by: Dec 12 2024)", task.toString());
    }

    @Test
    void testParseTask_validEvent() throws BobbyException {
        Task task = parser.parseTask("event meeting /from 2024-12-12 /to 2024-12-13");
        assertEquals("[E][ ] meeting (from: Dec 12 2024 to: Dec 13 2024)", task.toString());
    }

    @Test
    void testParseTask_invalidTodo() {
        Exception exception = assertThrows(EmptyTodoException.class, () -> {
            parser.parseTask("todo ");
        });
        assertEquals("The description of todo cannot be empty!", exception.getMessage());
    }

    @Test
    void testParseTask_invalidDeadline() {
        assertThrows(EmptyDeadlineException.class, () -> {
            parser.parseTask("deadline return book");
        });

        assertThrows(InvalidDateException.class, () -> {
            parser.parseTask("deadline return book /by invalid-date");
        });
    }

    @Test
    void testParseTask_invalidEvent() {
        assertThrows(EmptyEventException.class, () -> {
            parser.parseTask("event meeting /from 2024-12-12");
        });

        assertThrows(InvalidDateException.class, () -> {
            parser.parseTask("event meeting /from 2024-12-12 /to invalid-date");
        });
    }



}
