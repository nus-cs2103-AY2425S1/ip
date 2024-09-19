package gavinchatbot.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gavinchatbot.command.AddDeadlineCommand;
import gavinchatbot.command.AddEventCommand;
import gavinchatbot.command.AddToDoCommand;
import gavinchatbot.command.Command;
import gavinchatbot.command.DeleteCommand;
import gavinchatbot.command.ExitCommand;
import gavinchatbot.command.FindCommand;
import gavinchatbot.command.ListCommand;
import gavinchatbot.command.MarkCommand;
import gavinchatbot.command.TagCommand;
import gavinchatbot.command.UnmarkCommand;

class ParserTest {

    @Test
    void parseTodoCommand_validInput_createsAddToDoCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("todo read book");
        assertTrue(command instanceof AddToDoCommand);
    }

    @Test
    void parseInvalidCommand_throwsGavinException() {
        Parser parser = new Parser();
        assertThrows(GavinException.class, () -> parser.parse("invalid command"));
    }

    @Test
    void parseDeadlineCommand_validInput_createsAddDeadlineCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("deadline submit assignment /by 2020-12-31");
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    void parseEventCommand_validInput_createsAddEventCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("event project meeting /from 2020-12-01 /to 2020-12-02");
        assertTrue(command instanceof AddEventCommand);
    }

    @Test
    void parseMarkCommand_validInput_createsMarkCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void parseUnmarkCommand_validInput_createsUnmarkCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    void parseDeleteCommand_validInput_createsDeleteCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("delete 2");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void parseTagCommand_validInput_createsTagCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("tag 1 urgent");
        assertTrue(command instanceof TagCommand);
    }

    @Test
    void parseFindCommand_validInput_createsFindCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("find book");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    void parseListCommand_noInput_createsListCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void parseExitCommand_noInput_createsExitCommand() throws GavinException {
        Parser parser = new Parser();
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

}
