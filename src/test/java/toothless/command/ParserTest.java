package toothless.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;


/**
 * Tests for Parser class.
 */
public class ParserTest {
    /**
     * Tests if the parser can correctly parse the command.
     */
    @Test
    public void testParse() {
        assertInstanceOf(ToDoCommand.class, Parser.getCommandType("todo"));
        assertInstanceOf(DeadlineCommand.class, Parser.getCommandType("deadline"));
        assertInstanceOf(EventCommand.class, Parser.getCommandType("event"));
        assertInstanceOf(ListCommand.class, Parser.getCommandType("list"));
        assertInstanceOf(MarkDoneCommand.class, Parser.getCommandType("mark"));
        assertInstanceOf(MarkUndoneCommand.class, Parser.getCommandType("unmark"));
        assertInstanceOf(DeleteCommand.class, Parser.getCommandType("delete"));
        assertInstanceOf(UnknownCommand.class, Parser.getCommandType("random"));

    }

    /**
     * Tests if the parser can correctly parse the command with extra spaces.
     */
    @Test
    public void testParseWithExtraSpaces() {
        assertInstanceOf(ToDoCommand.class, Parser.getCommandType("  todo  "));
        assertInstanceOf(DeadlineCommand.class, Parser.getCommandType("  deadline  "));
        assertInstanceOf(EventCommand.class, Parser.getCommandType("  event  "));
        assertInstanceOf(ListCommand.class, Parser.getCommandType("  list  "));
        assertInstanceOf(MarkDoneCommand.class, Parser.getCommandType("  mark 1  "));
        assertInstanceOf(MarkUndoneCommand.class, Parser.getCommandType("  unmark 1  "));
        assertInstanceOf(DeleteCommand.class, Parser.getCommandType("  delete 1  "));
        assertInstanceOf(UnknownCommand.class, Parser.getCommandType("  random  "));
    }
}
