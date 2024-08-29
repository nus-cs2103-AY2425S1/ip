package nether.parser;

import org.junit.jupiter.api.Test;

import nether.NetherException;
import nether.command.*;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parse_validCommand_returnsCorrectCommand() throws NetherException {
        Command command = parser.parse("todo Read book");
        assertInstanceOf(AddCommand.class, command);

        command = parser.parse("mark 1");
        assertInstanceOf(MarkDoneCommand.class, command);
    }

    @Test
    public void parse_invalidCommand_throwsNetherException() {
        assertThrows(NetherException.class, () -> parser.parse("blah"));
    }

    @Test
    public void extractCommandWord_commandWords_returnsCorrectWOrds() {
        assertEquals("todo", parser.extractCommandWord("todo read book"));
        assertEquals("deadline", parser.extractCommandWord("deadline return book /by 2024-08-29 1500"));
        assertEquals("event", parser.extractCommandWord("event this /from 2024-08-29 1500 /to 2024-09-02 2359"));
    }

    @Test
    public void extractInputDetails_validTodoInput_returnsCorrectDetails() throws NetherException {
        String[] result = parser.extractInputDetails("todo Read book", "todo");
        assertArrayEquals(new String[]{"Read book"}, result);
    }

    @Test
    public void extractInputDetails_emptyTodoDescription_throwsNetherException() {
        NetherException exception = assertThrows(NetherException.class, () -> {
            parser.extractInputDetails("todo ", "todo");
        });
        assertEquals("the description of a todo cannot be empty.", exception.getMessage());
    }

    @Test
    public void extractInputDetails_validDeadlineInput_returnsCorrectDetails() throws NetherException {
        String[] result = parser.extractInputDetails("deadline Submit assignment /by 2024-09-01", "deadline");
        assertArrayEquals(new String[]{"Submit assignment ", "2024-09-01"}, result);
    }

    @Test
    public void extractInputDetails_missingDeadlineDescription_throwsNetherException() {
        NetherException exception = assertThrows(NetherException.class, () -> {
            parser.extractInputDetails("deadline /by 2024-09-01", "deadline");
        });
        assertEquals("the description or date/time of a deadline cannot be empty.", exception.getMessage());
    }

    @Test
    void extractInputDetails_missingDeadlineDate_throwsNetherException() {
        NetherException exception = assertThrows(NetherException.class, () -> {
            parser.extractInputDetails("deadline Submit assignment /by ", "deadline");
        });
        assertEquals("the description or date/time of a deadline cannot be empty.", exception.getMessage());
    }

    @Test
    void extractInputDetails_validEventInput_returnsCorrectDetails() throws NetherException {
        String[] result = parser.extractInputDetails("event Project meeting /from 2024-09-01 /to 2024-09-02", "event");
        assertArrayEquals(new String[]{"Project meeting ", "2024-09-01 ", "2024-09-02"}, result);
    }

    @Test
    void extractInputDetails_missingEventDescription_throwsNetherException() {
        NetherException exception = assertThrows(NetherException.class, () -> {
            parser.extractInputDetails("event /from 2024-09-01 /to 2024-09-02", "event");
        });
        assertEquals("the description, start time, or end time of an event cannot be empty.", exception.getMessage());
    }

    @Test
    void extractInputDetails_missingEventDates_throwsNetherException() {
        NetherException exception = assertThrows(NetherException.class, () -> {
            parser.extractInputDetails("event Project meeting /from /to", "event");
        });
        assertEquals("the description, start time, or end time of an event cannot be empty.", exception.getMessage());
    }

    // Tests for the extractTaskNumber method

    @Test
    void extractTaskNumber_validInput_returnsCorrectTaskNumber() {
        int result = parser.extractTaskNumber("mark 3");
        assertEquals(3, result);
    }

    @Test
    void extractTaskNumber_invalidNumberFormat_returnsMinusOne() {
        int result = parser.extractTaskNumber("mark one");
        assertEquals(-1, result);
    }

    @Test
    void extractTaskNumber_missingTaskNumber_returnsMinusOne() {
        int result = parser.extractTaskNumber("mark");
        assertEquals(-1, result);
    }
}
