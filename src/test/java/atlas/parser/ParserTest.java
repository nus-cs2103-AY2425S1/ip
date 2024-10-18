package atlas.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import atlas.commands.Command;
import atlas.commands.ExitCommand;
import atlas.commands.ListCommand;
import atlas.commands.MarkCommand;
import atlas.commands.UnmarkCommand;
import atlas.exceptions.AtlasException;

/**
 * Tests for the Parser class to ensure correct command parsing functionality.
 */
public class ParserTest {

    /**
     * Tests that the "bye" input correctly parses to an ExitCommand.
     *
     * @throws AtlasException if there is an error during parsing.
     */
    @Test
    public void exitCommand_byeInput_success() throws AtlasException {
        Command c = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    /**
     * Tests that the "list" input correctly parses to a ListCommand.
     *
     * @throws AtlasException if there is an error during parsing.
     */
    @Test
    public void listCommand_listInput_success() throws AtlasException {
        Command c = Parser.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    /**
     * Tests that the "mark" command with a valid index correctly parses to a MarkCommand.
     *
     * @throws AtlasException if there is an error during parsing.
     */
    @Test
    public void markCommand_indexInput_success() throws AtlasException {
        Command c = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, c);
    }

    /**
     * Tests that the "mark" command without an index throws an AtlasException.
     */
    @Test
    public void markCommand_missingIndexInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("mark"));
    }

    /**
     * Tests that the "mark" command with additional arguments throws an AtlasException.
     */
    @Test
    public void markCommand_additionalArgumentsInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("mark 1 1"));
    }

    /**
     * Tests that the "unmark" command with a valid index correctly parses to an UnmarkCommand.
     *
     * @throws AtlasException if there is an error during parsing.
     */
    @Test
    public void unmarkCommand_indexInput_success() throws AtlasException {
        Command c = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, c);
    }

    /**
     * Tests that the "unmark" command without an index throws an AtlasException.
     */
    @Test
    public void unmarkCommand_missingIndexInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("unmark"));
    }

    /**
     * Tests that the "unmark" command with additional arguments throws an AtlasException.
     */
    @Test
    public void unmarkCommand_additionalArgumentsInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("unmark 1 1"));
    }
}
