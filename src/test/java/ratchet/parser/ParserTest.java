package ratchet.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ratchet.command.AddCommand;
import ratchet.command.Command;
import ratchet.exception.RatchetException;

public class ParserTest {
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            new Parser().parse("blah");
            fail();
        } catch (RatchetException e) {
            assertEquals("Invalid command: Ratchet is unable to execute blah!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidTodoArguments_exceptionThrown() {
        try {
            new Parser().parse("todo");
            fail();
        } catch (RatchetException e) {
            assertEquals("Invalid command argument: The description of a todo task cannot be empty!",
                    e.getMessage());
        }
    }

    @Test
    public void parse_validTodoArguments_success() throws RatchetException {
        Command command = new Parser().parse("todo test");
        assertEquals(command.getClass(), AddCommand.class);
    }

    @Test
    public void parse_validDeadlineArguments_success() throws RatchetException {
        Command command = new Parser().parse("deadline test /by 2000-01-01");
        assertEquals(command.getClass(), AddCommand.class);
    }

    @Test
    public void parse_validEventArguments_success() throws RatchetException {
        Command command = new Parser().parse("event test /from 2000-01-01 /to 2000-12-12");
        assertEquals(command.getClass(), AddCommand.class);
    }
}
