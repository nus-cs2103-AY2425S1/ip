package luke;
import luke.command.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseSavedMarkedCommand() {
        Parser parser = new Parser();
        Command command = parser.parseSavedData("X | todo | read book");
        assertEquals("x", command.getMark());
        assertEquals("todo", command.getCommand());
        assertEquals("read book", command.getArgs());
    }

    @Test
    public void testParseSavedUnmarkedCommand() {
        Parser parser = new Parser();
        Command command = parser.parseSavedData("- | todo | read book");
        assertEquals("-", command.getMark());
        assertEquals("todo", command.getCommand());
        assertEquals("read book", command.getArgs());
    }

    @Test
    public void testParseInputCommand() {
        Parser parser = new Parser();
        Command command = parser.parseInputData("todo read book");
        assertEquals("-", command.getMark());
        assertEquals("todo", command.getCommand());
        assertEquals("read book", command.getArgs());
    }
}
