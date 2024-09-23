package trackie.parsing;

import org.junit.jupiter.api.Test;
import trackie.commands.Command;
import trackie.commands.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

public class ParserTest {
    @Test
    public void testCommandParsing() {
        String userInput = "lmao";
        Command c = Parser.parseCommand(userInput);
        assertEquals(c instanceof InvalidCommand, true);
    }

    @Test
    public void testParsingResult() {
        String userInput = "t      ";
        Command c = Parser.parseCommand(userInput);
        assertEquals(c.getArguments().length, 1);
    }
}
