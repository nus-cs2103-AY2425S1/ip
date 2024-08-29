
package arts.util;

import arts.ArtsException;
import arts.enums.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testParseCommand_validCommand() throws ArtsException {
        assertEquals(CommandType.TODO, parser.parseCommand("todo task"), "The command should be parsed as TODO.");
    }

    @Test
    public void testParseCommand_invalidCommand() {
        assertThrows(ArtsException.class, () -> parser.parseCommand("unknown"), "An unknown command should throw an ArtsException.");
    }

    @Test
    public void testParseArguments() {
        String[] expected = {"todo", "task"};
        assertArrayEquals(expected, parser.parseArguments("todo task"), "The arguments should be split correctly.");
    }
}