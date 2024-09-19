package pixel;

import org.junit.jupiter.api.Test;
import pixel.command.AddCommand;
import pixel.command.Command;
import pixel.command.DeleteCommand;
import pixel.command.ExitCommand;
import pixel.command.ListCommand;
import pixel.command.MarkCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests the parsing of the "bye" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseExitCommand() throws PixelException {
        Command command = Parser.parser("bye");
        assertTrue(command instanceof ExitCommand);
    }

    /**
     * Tests the parsing of the "list" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseListCommand() throws PixelException {
        Command command = Parser.parser("list");
        assertTrue(command instanceof ListCommand);
    }

    /**
     * Tests the parsing of the "mark" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseMarkCommand() throws PixelException {
        String input = "mark 1";
        Command command = Parser.parser(input);
        assertTrue(command instanceof MarkCommand);
    }

    /**
     * Tests the parsing of the "unmark" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseUnmarkCommand() throws PixelException {
        String input = "unmark 2";
        Command command = Parser.parser(input);
        assertTrue(command instanceof MarkCommand);
    }

    /**
     * Tests the parsing of the "todo" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseTodoCommand() throws PixelException {
        String input = "todo read book";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    /**
     * Tests the parsing of the "deadline" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseDeadlineCommand() throws PixelException {
        String input = "deadline submit assignment /by 2022-09-01";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    /**
     * Tests the parsing of the "event" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseEventCommand() throws PixelException {
        String input = "event team meeting /from 2022-09-01 /to 2022-09-02";
        Command command = Parser.parser(input);
        assertTrue(command instanceof AddCommand);
    }

    /**
     * Tests the parsing of the "delete" command.
     *
     * @throws PixelException if an error occurs during parsing.
     */
    @Test
    public void parseDeleteCommand() throws PixelException {
        String input = "delete 3";
        Command command = Parser.parser(input);
        assertTrue(command instanceof DeleteCommand);
    }

    /**
     * Tests the parsing of an unknown command.
     */
    @Test
    public void parseUnknownCommand() {
        String input = "unknown command";
        PixelException thrown = assertThrows(PixelException.class, () -> {
            Parser.parser(input);
        });
        assertEquals("OH NO!!! I don't understand 'UNKNOWN'! Try Again!", thrown.getMessage());
    }

    /**
     * Tests the parsing of an empty command.
     */
    @Test
    public void parseEmptyCommand() {
        String input = "";
        PixelException thrown = assertThrows(PixelException.class, () -> {
            Parser.parser(input);
        });
        assertEquals("OH NO!!! I don't understand ''! Try Again!", thrown.getMessage());
    }
}
