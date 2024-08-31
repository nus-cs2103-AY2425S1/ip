import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import command.Command;
import psu.Parser;

public class ParserTest {
    @Test
    public void test1() {
        Parser p = new Parser();
        Command c = p.parse("bye");
        assertTrue(c.isExitCommand());
    }

    @Test
    public void test2() {
        Parser p = new Parser();
        Command c = p.parse("sdfj");
        assertTrue(c.isErrorCommand());
    }

    @Test
    public void test3() {
        Parser p = new Parser();
        Command c = p.parse("mark 3");
        assertFalse(c.isErrorCommand());
    }

    @Test
    public void test4() {
        Parser p = new Parser();
        Command c = p.parse("mark 3");
        assertFalse(c.isExitCommand());
    }
}
