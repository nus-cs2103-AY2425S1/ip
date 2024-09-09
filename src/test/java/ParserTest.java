import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import command.Command;
import psu.Parser;

public class ParserTest {
    @Test
    public void testIsExitCommand() {
        Command c = Parser.parse("bye");
        assertTrue(c.isExitCommand());
    }

    @Test
    public void testIsErrorCommand() {
        Command c = Parser.parse("sdfj");
        assertTrue(c.isErrorCommand());
    }

    @Test
    public void testNotIsErrorCommand() {
        Command c = Parser.parse("mark 3");
        assertFalse(c.isErrorCommand());
    }

    @Test
    public void testNotIsExitCommand() {
        Command c = Parser.parse("mark 3");
        assertFalse(c.isExitCommand());
    }
}
