package yihuibot.ui;

import org.junit.jupiter.api.Test;

import yihuibot.exception.parse.ParseException;

import yihuibot.executable.AddTask;
import yihuibot.executable.DeleteTask;
import yihuibot.executable.Exit;
import yihuibot.executable.ListTask;
import yihuibot.executable.MarkTask;
import yihuibot.executable.UnmarkTask;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Test for Parser.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class ParserTest {
    private Parser parser;

    /**
     * Ensures that Parser is not null.
     */
    @Test
    public void constructor_notNull() {
        parser = new Parser("yyyy-MM-dd HH:mm");
        assertNotNull(parser);
    }

    /**
     * Ensures that parse returns the correct Executable.
     */
    @Test
    public void parse_goodInput_returnsCorrectExecutable() {
        parser = new Parser("yyyy-MM-dd HH:mm");
        try {
            assertInstanceOf(AddTask.class, parser.parse("todo foo"));
            assertInstanceOf(DeleteTask.class, parser.parse("delete 1"));
            assertInstanceOf(Exit.class, parser.parse("bye"));
            assertInstanceOf(ListTask.class, parser.parse("list"));
            assertInstanceOf(MarkTask.class, parser.parse("mark 1"));
            assertInstanceOf(UnmarkTask.class, parser.parse("unmark 1"));
        } catch (ParseException e) {
            fail();
        }
    }
}
