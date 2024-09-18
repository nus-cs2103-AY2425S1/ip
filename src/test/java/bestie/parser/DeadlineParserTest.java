package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.task.Deadline;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeadlineParserTest {

    @Test
    void constructor_missingDescription_expectedErrorMesage() {
        String expectedErrorMessage = "You did not input the deadline in a valid format." +
                " Please follow the format \"deadline (name of task) /by (deadline) " +
                "/priority (high/medium/low)\".\n Please stick to the correct format: YYYY-MM-DD HHMM " +
                "for the deadline";

        DeadlineParser deadlineParser = new DeadlineParser();
        Command command = deadlineParser.executeDeadlineCommand("deadline");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(errorCommand.getErrorMessage(), expectedErrorMessage);
        }
    }

    @Test
    void constructor_missingDeadline_expectedErrorMesage() {
        String expectedErrorMessage = "You did not input the deadline in a valid format." +
                " Please follow the format \"deadline (name of task) /by (deadline) " +
                "/priority (high/medium/low)\".\n Please stick to the correct format: YYYY-MM-DD HHMM " +
                "for the deadline";

        DeadlineParser deadlineParser = new DeadlineParser();
        Command command = deadlineParser.executeDeadlineCommand("deadline submit homework");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(errorCommand.getErrorMessage(), expectedErrorMessage);
        }
    }

    @Test
    void constructor_missingPriority_expectedErrorMesage() {
        String expectedErrorMessage = "Invalid priority :(. Please specify as 'high', 'medium' or 'low'.";
        DeadlineParser deadlineParser = new DeadlineParser();
        Command command = deadlineParser.executeDeadlineCommand("deadline submit homework " +
                "/by 2024-09-19 1900 /priority ");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(errorCommand.getErrorMessage(), expectedErrorMessage);
        }
    }

    @Test
    void constructor_prioritySpelledWrongly_expectedErrorMesage() {
        String expectedErrorMessage = "Invalid priority :(. Please specify as 'high', 'medium' or 'low'.";
        DeadlineParser deadlineParser = new DeadlineParser();
        Command command = deadlineParser.executeDeadlineCommand("deadline submit homework " +
                "/by 2024-09-19 1900 /priority lw");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(errorCommand.getErrorMessage(), expectedErrorMessage);
        }
    }

    @Test
    void constructor_invalidDeadline_expectedErrorMessage() {
        String expectedErrorMessage = "You did not input the date and time in the correct format, or it does not exist." +
                " Please stick to the correct format: YYYY-MM-DD HHMM";
        DeadlineParser deadlineParser = new DeadlineParser();
        Command command = deadlineParser.executeDeadlineCommand("deadline submit homework " +
                "/by 2024-09-194 1900 /priority low");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(errorCommand.getErrorMessage(), expectedErrorMessage);
        }
    }

}
