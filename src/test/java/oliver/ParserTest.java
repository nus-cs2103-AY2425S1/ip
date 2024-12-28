package oliver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseCommand() {
        String todo = "todo read books";
        String ls = "list";
        assertEquals("todo", Parser.parseCommand(todo));
        assertEquals("list", Parser.parseCommand(ls));
    }

    @Test
    public void testParseArgs() {
        String todo = "todo read books";
        String deadline = "deadline /by 2024-08-24 1800";
        String event = "event /from 2024-08-24 1800 /to 2024-08-24 1900";
        assertEquals("read books", Parser.parseArgs(todo));
        assertEquals("/by 2024-08-24 1800", Parser.parseArgs(deadline));
        assertEquals("/from 2024-08-24 1800 /to 2024-08-24 1900", Parser.parseArgs(event));
    }
}
