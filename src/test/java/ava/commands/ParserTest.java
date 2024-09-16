package ava.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
class ParserTest {

    @Test
    void testParseCommand() {
        assertEquals(Command.LIST, Parser.parseCommand("list"));
        assertEquals(Command.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Command.UNMARK, Parser.parseCommand("unmark 1"));
        assertEquals(Command.DELETE, Parser.parseCommand("delete 1"));
        assertEquals(Command.TODO, Parser.parseCommand("todo read book"));
        assertEquals(Command.DEADLINE, Parser.parseCommand("deadline submit report /by Sunday"));
        assertEquals(Command.EVENT, Parser.parseCommand("event team meeting /at 2pm"));
        assertEquals(Command.FIND, Parser.parseCommand("find book"));

        // Test for unsupported command
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseCommand("unsupported command");
        });
        assertEquals("Unsupported Command", exception.getMessage());
    }
}