import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import pixel.command.AddCommand;
import pixel.command.Command;
import pixel.command.DeleteCommand;
import pixel.command.ExitCommand;
import pixel.command.ListCommand;
import pixel.command.MarkCommand;
import pixel.command.PixelCommandEnum;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Todo;
import pixel.Parser;
import pixel.PixelException;

public class ParserTest {

    @Test
    public void parseExitCommand() throws PixelException {
        Command command = Parser.parser("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseListCommand() throws PixelException {
        Command command = Parser.parser("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseMarkCommand() throws PixelException {
        String input = "mark 1";
        Command command = Parser.parser(input);
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseUnmarkCommand() throws PixelException {
        String input = "unmark 2";
        Command command = Parser.parser(input);
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseTodoCommand() throws PixelException {
        String input = "todo read book";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseDeadlineCommand() throws PixelException {
        String input = "deadline submit assignment /by 2022-09-01";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseEventCommand() throws PixelException {
        String input = "event team meeting /from 2022-09-01 /to 2022-09-02";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseDeleteCommand() throws PixelException {
        String input = "delete 3";
        Command command = Parser.parser(input);
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseUnknownCommand() {
        String input = "unknown command";
        PixelException thrown = assertThrows(PixelException.class, () -> {
            Parser.parser(input);
        });
        assertEquals("OH NO!!! I don't understand this! Try Again!", thrown.getMessage());
    }

    @Test
    public void parseEmptyCommand() {
        String input = "";
        PixelException thrown = assertThrows(PixelException.class, () -> {
            Parser.parser(input);
        });
        assertEquals("OH NO!!! I don't understand this! Try Again!", thrown.getMessage());
    }
}
