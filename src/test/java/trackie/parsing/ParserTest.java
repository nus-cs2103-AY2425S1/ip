package trackie.parsing;

import org.junit.jupiter.api.Test;
import trackie.commands.Command;
import trackie.commands.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testCommandParsing() {
        String userInput = "lsit";
        Command c = Parser.parseCommand(userInput);
        assertEquals(c instanceof InvalidCommand, true);
    }

    @Test
    public void testCommandParsing2() {
        String userInput = "bye";
        Command c = Parser.parseCommand(userInput);
        assertEquals(c instanceof ExitCommand, true);
    }

    @Test
    public void testParsingResult() {
        String userInput = "todo       ";
        Command c = Parser.parseCommand(userInput);
        assertEquals(c.getArguments().length, 1);
    }
}
