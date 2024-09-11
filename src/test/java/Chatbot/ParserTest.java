package Chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testParseCommandBye() {
        assertEquals(Command.BYE, parser.parseCommand("bye"));
    }

    @Test
    public void testParseCommandList() {
        assertEquals(Command.LIST, parser.parseCommand("list"));
    }

    @Test
    public void testParseCommandMark() {
        assertEquals(Command.MARK, parser.parseCommand("mark 1"));
    }

    @Test
    public void testParseCommandTodo() {
        assertEquals(Command.TODO, parser.parseCommand("todo read book"));
    }

    @Test
    public void testParseCommandDeadline() {
        assertEquals(Command.DEADLINE, parser.parseCommand("deadline submit report /by 2024-09-01 12:00"));
    }

    @Test
    public void testParseCommandEvent() {
        assertEquals(Command.EVENT, parser.parseCommand("event project meeting /from 2024-09-01 09:00 /to 2024-09-01 10:00"));
    }

    @Test
    public void testParseCommandDelete() {
        assertEquals(Command.DELETE, parser.parseCommand("delete 3"));
    }

    @Test
    public void testParseCommandUnknown() {
        assertEquals(Command.UNKNOWN, parser.parseCommand("unknown command"));
    }
}
