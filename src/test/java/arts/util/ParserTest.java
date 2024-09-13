package arts.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.enums.CommandType;

/**
 * Test class for the Parser class.
 * This class contains unit tests for methods in the Parser class, verifying command parsing functionality.
 */
public class ParserTest {

    private final Parser parser = new Parser();

    /**
     * Tests the parseCommand() method with a valid command.
     * Verifies that the command string is correctly parsed into a CommandType.
     *
     * @throws ArtsException if the command cannot be parsed.
     */
    @Test
    public void testParseCommand_validCommand() throws ArtsException {
        assertEquals(CommandType.TODO, parser.parseCommand("todo task"),
                "The command should be parsed as TODO.");
    }

    /**
     * Tests the parseCommand() method with an invalid command.
     * Ensures that an ArtsException is thrown when an unknown command is parsed.
     */
    @Test
    public void testParseCommand_invalidCommand() {
        assertThrows(ArtsException.class, () -> parser.parseCommand("unknown"),
                "An unknown command should throw an ArtsException.");
    }

    /**
     * Tests the parseArguments() method.
     * Verifies that the input string is correctly split into an array of arguments.
     */
    @Test
    public void testParseArguments() {
        String[] expected = {"todo", "task"};
        assertArrayEquals(expected, parser.parseArguments("todo task"),
                "The arguments should be split correctly.");
    }
}
