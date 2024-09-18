package sirpotato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    @Test
    public void parseCommand_validTodoCommand_returnsAddCommand() throws DukeException {

        String userInput = "todo Read book";
        Command command = Parser.parseCommand(userInput);

        // Make sure it gives an add command and not any other command
        assertTrue(command instanceof AddCommand);

        // Make sure the correct task is added and the description is properly extracted
        AddCommand addCommand = (AddCommand) command;
        Todo todo = (Todo) addCommand.getTask();
        assertEquals("Read book", todo.displayDescription());
    }

    @Test
    public void parseCommand_byeCommand_returnsExitCommand() throws DukeException {

        String userInput = "bye";
        Command command = Parser.parseCommand(userInput);

        // Make sure an exit command is generated
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_invalidCommand_throwsDukeException() {
        //Invalid command because no description given
        String userInput = "todo ";

        //Must throw a Duke Exception
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parseCommand(userInput);
        });

        assertEquals("Mate, you have got to give us a description of the task", exception.getMessage());
    }
}
