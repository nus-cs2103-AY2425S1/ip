package streams.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.command.*;
import streams.exception.StreamsException;

public class ParserTest {

    @Test
    public void testParseToDoCommand() throws StreamsException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws StreamsException {
        Command command = Parser.parse("deadline Submit report /by 2023-12-31 23:59");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseEventCommand() throws StreamsException {
        Command command = Parser.parse("event Team meeting /from 2023-12-25 10:00 /to 2023-12-25 12:00");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseListCommand() throws StreamsException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(StreamsException.class, () -> Parser.parse("invalid command"));
    }
}