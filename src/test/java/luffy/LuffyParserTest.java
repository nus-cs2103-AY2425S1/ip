package luffy;
import luffy.command.*;
import luffy.parser.LuffyParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class LuffyParserTest {

    @Test
    public void testParseAddTodoCommand() {
        System.out.println("Test 1 loaded.");
        Command command = LuffyParser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() {
        System.out.println("Test 2 loaded.");
        Command command = LuffyParser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() {
        System.out.println("Test 3 loaded.");
        Command command = LuffyParser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        System.out.println("Test 4 loaded.");
        Command command = LuffyParser.parse("invalid command");
        assertInstanceOf(InvalidCommand.class, command);
    }
}
