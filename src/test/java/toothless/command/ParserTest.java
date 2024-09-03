package toothless.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
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
