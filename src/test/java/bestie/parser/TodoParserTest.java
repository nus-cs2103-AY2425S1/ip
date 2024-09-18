package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TodoParserTest {

    @Test
    void executeCommand_missingDescription_correctErrorMessage() {
        TodoParser parser = new TodoParser();
        String expectedErrorMessage = "The description of a todo cannot be empty.\n" +
                "Please input your todo again!";
        Command command = parser.executeCommand("todo");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(expectedErrorMessage, errorCommand.getErrorMessage());
        }
    }

    @Test
    void executeCommand_missingDescription2_correctErrorMessage() {
        TodoParser parser = new TodoParser();
        String expectedErrorMessage = "The description of a todo cannot be empty.\n" +
                "Please input your todo again!";
        Command command = parser.executeCommand("todo /priority low");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(expectedErrorMessage, errorCommand.getErrorMessage());
        }
    }

    @Test
    void executeCommand_missingPriority_correctErrorMessage() {
        TodoParser parser = new TodoParser();
        String expectedErrorMessage = "You did not key in the priority of your task! Remember to indicate " +
                "the priority with the command \"/priority\"";
        Command command = parser.executeCommand("todo quiz");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(expectedErrorMessage, errorCommand.getErrorMessage());
        }
    }

    @Test
    void executeCommand_invalidPriorityLevel_correctErrorMessage() {
        TodoParser parser = new TodoParser();
        String expectedErrorMessage = "Invalid priority :(. Please specify as 'high', 'medium' or 'low'.";
        Command command = parser.executeCommand("todo quiz /priority whatever");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(expectedErrorMessage, errorCommand.getErrorMessage());
        }

    }
}
