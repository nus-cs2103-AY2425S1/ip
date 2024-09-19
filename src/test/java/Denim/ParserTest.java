package denim;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import denim.commands.Command;
import denim.commands.DeadlineCommand;
import denim.commands.EventCommand;
import denim.commands.FindCommand;
import denim.commands.InvalidCommand;
import denim.commands.ListCommand;
import denim.commands.TodoCommand;
import denim.commands.UnmarkCommand;

public class ParserTest {

    private final Parser parser = new Parser();
    @Test
    public void prepareEvent_validInput_returnsEventCommand() {
        String simulatedInput = "event dinner /from 19/02/2002 1900 /to 19/02/2002 2200";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void prepareEvent_invalidDateTimeFormat_returnsInvalidCommand() {
        String simulatedInput = "event dinner /from 19-02-2002 1900 /to 19-02-2002 2200";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareEvent_impossibleDateTime_returnsInvalidCommand() {
        String simulatedInput = "event dinner /from 19/13/2004 1800 /to 19/13/2004 2000";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareEvent_missingTime_returnsInvalidCommand() {
        String simulatedInput = "event wilderness camping /from 19/02/2002 /to 20/02/2002";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareEvent_missingDate_returnsInvalidCommand() {
        String simulatedInput = "event dinner /from 1900 /to 2200";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareDeadline_validInput_returnsDeadlineCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 19/04/2002 1900";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void prepareDeadline_invalidDateTimeFormat_returnsInvalidCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 19-04-2002 1900";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareDeadline_impossibleDateTime_returnsInvalidCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 40/20/2002 19000";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareDeadline_missingTime_returnsInvalidCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 19/04/2002";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareDeadline_missingDate_returnsInvalidCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 1900";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareTodo_validInput_returnsTodoCommand() {
        String simulatedInput = "todo Read a book";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void prepareTodo_missingDescription_returnsInvalidCommand() {
        String simulatedInput = "todo ";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareTodo_emptyDescription_returnsInvalidCommand() {
        String simulatedInput = "todo    ";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareMark_nonNumericInput_returnsInvalidCommand() {
        String simulatedInput = "mark two";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }


    @Test
    public void prepareMark_negativeIndex_returnsInvalidCommand() {
        String simulatedInput = "mark -1";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareUnmark_validInput_returnsUnmarkCommand() {
        String simulatedInput = "unmark 3";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void prepareDelete_nonNumericInput_returnsInvalidCommand() {
        String simulatedInput = "delete one";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareList_validInput_returnsListCommand() {
        String simulatedInput = "list";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void prepareDeadline_extraArguments_returnsInvalidCommand() {
        String simulatedInput = "deadline MT2000 Assignment 0 /by 19/04/2002 1900 extra";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareFind_keywordsWithSpecialCharacters_returnsFindCommand() {
        String simulatedInput = "find project-deadline";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void prepareInvalidCommand_whitespaceOnly_returnsInvalidCommand() {
        String simulatedInput = "   ";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    public void prepareFind_specialCharacters_returnsFindCommand() {
        String simulatedInput = "find project@2023";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void prepareHelp_incorrectUsage_returnsInvalidCommand() {
        String simulatedInput = "help me please";

        Command command = parser.parseCommand(simulatedInput);
        assertTrue(command instanceof InvalidCommand);
    }
}
