package denim;

import denim.commands.Command;
import denim.commands.DeadlineCommand;
import denim.commands.EventCommand;
import denim.commands.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
