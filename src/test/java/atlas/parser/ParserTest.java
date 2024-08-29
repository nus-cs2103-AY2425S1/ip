package atlas.parser;

import atlas.commands.Command;
import atlas.commands.ExitCommand;
import atlas.commands.ListCommand;
import atlas.commands.MarkCommand;
import atlas.commands.UnmarkCommand;

import atlas.exceptions.AtlasException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void exitCommand_byeInput_success() throws AtlasException {
        Command c = Parser.parse("bye");
        assert(c instanceof ExitCommand);
    }

    @Test
    public void listCommand_listInput_success() throws AtlasException {
        Command c = Parser.parse("list");
        assert(c instanceof ListCommand);
    }

    @Test
    public void markCommand_IndexInput_success() throws AtlasException {
        Command c = Parser.parse("mark 1");
        assert(c instanceof MarkCommand);
    }

    @Test
    public void markCommand_missingIndexInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void markCommand_additionalArgumentsInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("mark 1 1"));
    }

    @Test
    public void unmarkCommand_IndexInput_success() throws AtlasException {
        Command c = Parser.parse("unmark 1");
        assert(c instanceof UnmarkCommand);
    }

    @Test
    public void unmarkCommand_missingIndexInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void unmarkCommand_additionalArgumentsInput_error() {
        assertThrows(AtlasException.class, () -> Parser.parse("unmark 1 1"));
    }
}