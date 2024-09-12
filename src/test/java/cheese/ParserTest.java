package cheese;

import cheese.exception.CheeseException;
import cheese.exception.InputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cheese.command.*;

/**
 * Tests for the Parser class in the cheese package
 */
public class ParserTest {
    //Tests for main parser function
    @Test
    public void testParseByeCommand() throws CheeseException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye", 0));

    }
    @Test
    public void testParseListCommand() throws CheeseException {
        Command command = Parser.parse("list", 10);
        assertInstanceOf(ListCommand.class, command);
    }
    @Test
    public void testParseMarkCommand() throws CheeseException {
        Command command = Parser.parse("mark 1", 10);
        assertInstanceOf(MarkCommand.class, command);
    }
    @Test
    public void testParseUnmarkCommand() throws CheeseException {
        Command command = Parser.parse("unmark 1", 10);
        assertInstanceOf(MarkCommand.class, command);
    }
    @Test
    public void testParseTodoCommand() throws CheeseException {
        Command command = Parser.parse("todo Read a book", 10);
        assertInstanceOf(AddCommand.class, command);
    }
    @Test
    public void testParseDeadlineCommand() throws CheeseException {
        Command command = Parser.parse("deadline Submit report /by 2023-03-25", 10);
        assertInstanceOf(AddCommand.class, command);
    }
    @Test
    public void testParseEventCommand() throws CheeseException {
        Command command = Parser.parse("event Meeting /from 2024-03-05 /to 2024-04-04", 10);
        assertInstanceOf(AddCommand.class, command);
    }
    @Test
    public void testParseDeleteCommand() throws CheeseException {
        Command command = Parser.parse("delete 1", 10);
        assertInstanceOf(UpdateCommand.class, command);
    }
    @Test
    public void testParseDefaultCommand() throws CheeseException {
        Command command = Parser.parse("unknown command", 10);
        assertInstanceOf(AddCommand.class, command);
    }

    // Tests for getIdx
    @Test
    public void testGetIdxValidInput() throws CheeseException {
        String[] inputTokens = {"mark", "2"};
        int size = 5;
        int idx = Parser.getIdx(inputTokens, size);
        assertEquals(1, idx);
    }

    @Test
    public void testGetIdxInvalidInputLength() {
        String[] inputTokens = {"mark"};
        int size = 5;
        CheeseException exception = assertThrows(InputException.class, () -> {
            Parser.getIdx(inputTokens, size);
        });
    }

    @Test
    public void testGetIdxInvalidNumberFormat() {
        String[] inputTokens = {"mark", "two"};
        int size = 5;
        CheeseException exception = assertThrows(CheeseException.class, () -> {
            Parser.getIdx(inputTokens, size);
        });
        assertEquals("For input string: \"two\"", exception.getMessage());
    }

    @Test
    public void testGetIdxIndexOutOfBoundsHigh() {
        String[] inputTokens = {"mark", "10"};
        int size = 5;
        CheeseException exception = assertThrows(CheeseException.class, () -> {
            Parser.getIdx(inputTokens, size);
        });
        assertEquals("Incorrect location of cheese", exception.getMessage());
    }

    @Test
    public void testGetIdxIndexOutOfBoundsLow() {
        String[] inputTokens = {"mark", "0"};
        int size = 5;
        CheeseException exception = assertThrows(CheeseException.class, () -> {
            Parser.getIdx(inputTokens, size);
        });
        assertEquals("Incorrect location of cheese", exception.getMessage());
    }
}
