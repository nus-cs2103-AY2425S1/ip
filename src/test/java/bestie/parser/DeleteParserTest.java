package bestie.parser;

import bestie.command.Command;
import bestie.command.DeleteCommand;
import bestie.command.ErrorCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeleteParserTest {


    @Test
    void executeDeleteCommand_normalInput_deleteCommandExecuted() {
        DeleteParser deleteParser = new DeleteParser();
        Command command = deleteParser.executeDeleteCommand("delete 4");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void executeDeleteCommand_missingIndex_correctErrorMessage() {
        DeleteParser deleteParser = new DeleteParser();
        String expectedErrorMessage = "You need to key in the index of the task you are deleting!!";
        Command command = deleteParser.executeDeleteCommand("delete");
        assertInstanceOf(ErrorCommand.class, command);
        if (command instanceof ErrorCommand) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            assertEquals(expectedErrorMessage, errorCommand.getErrorMessage());
        }
    }
}
