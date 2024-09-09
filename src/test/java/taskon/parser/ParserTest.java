package taskon.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskon.common.Messages.MESSAGE_DATE_MISSING;
import static taskon.common.Messages.MESSAGE_DESCRIPTION_MISSING;
import static taskon.common.Messages.MESSAGE_HELP;
import static taskon.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskon.commands.ByeCommand;
import taskon.commands.Command;
import taskon.commands.DeadlineCommand;
import taskon.commands.EventCommand;
import taskon.commands.IncorrectCommand;
import taskon.commands.ListCommand;
import taskon.commands.TodoCommand;
import taskon.exception.TaskonException;



public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setParser() {
        parser = new Parser();
    }

    @Test
    void parse_validByeCommand_parsedCorrectly() throws TaskonException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void parse_validListCommand_parsedCorrectly() throws TaskonException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void parse_validTodoCommand_parsedCorrectly() throws TaskonException {
        Command command = Parser.parse("todo study for test");
        assertTrue(command instanceof TodoCommand);
        assertEquals("[T][ ] study for test", ((TodoCommand) command).getTask().toString());
    }

    @Test
    void parse_invalidTodoCommand_exceptionThrown() {
        TaskonException exception = assertThrows(TaskonException.class, () -> {
            Parser.parse("todo ");
        });
        assertEquals(MESSAGE_DESCRIPTION_MISSING, exception.getMessage());
    }

    @Test
    void parse_validDeadlineCommand_parsedCorrectly() throws TaskonException {
        Command command = Parser.parse("deadline submit assignment /by 2024-09-01 1800");
        assertTrue(command instanceof DeadlineCommand);
        assertEquals("[D][ ] submit assignment (by: Sep 1 2024, 6:00 pm)", ((DeadlineCommand) command)
                .getTask().toString());
    }

    @Test
    void parse_invalidDeadlineCommandMissingDate() {
        TaskonException exception = assertThrows(TaskonException.class, () -> {
            Parser.parse("deadline submit assignment");
        });
        assertEquals(MESSAGE_DATE_MISSING, exception.getMessage());
    }

    @Test
    void parse_validEventCommand_parsedCorrectly() throws TaskonException {
        Command command = Parser.parse("event project meeting /from 2024-09-01 1400 /to 2024-09-01 1600");
        assertTrue(command instanceof EventCommand);
        assertEquals(
                "[E][ ] project meeting (from: Sep 1 2024, 2:00 pm to: Sep 1 2024, 4:00 pm)", ((EventCommand) command)
                .getTask().toString());
    }

    @Test
    void parse_invalidEventCommandMissingDate_exceptionThrown() {
        TaskonException exception = assertThrows(TaskonException.class, () -> {
            Parser.parse("event project meeting /from 2024-09-01 2pm");
        });
        assertEquals(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_HELP, exception.getMessage());
    }

    @Test
    void testParse_invalidCommand() throws TaskonException {
        Command command = Parser.parse("invalidcommand");
        assertTrue(command instanceof IncorrectCommand);
    }
}
