package Joseph;

import Joseph.Exceptions.InsufficientDetailsException;
import Joseph.Exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class ParserTest {

    @Test
    public void testParseCommand() throws UnknownCommandException  {
        Parser parser = new Parser();

        assertEquals(Parser.Command.LIST, parser.parseCommand("list"));
        assertEquals(Parser.Command.MARK, parser.parseCommand("mark 1"));
        assertEquals(Parser.Command.TODO, parser.parseCommand("todo lunch"));
    }

    @Test
    public void testParseEmptyTodoDetails() {
        Parser parser = new Parser();

        assertThrows(InsufficientDetailsException.class,
                () -> parser.parseTodoDetails("todo ", Parser.Command.TODO.toString()));
    }

    @Test
    public void testParseEventDetails() throws InsufficientDetailsException {
        Parser parser = new Parser();
        String[] details = parser.parseEventDetails(
                "event lect /01/01/2024 1100 /01/01/2024 1300",
                Parser.Command.EVENT.toString());

        assertEquals("lect", details[0]);
        assertEquals("01/01/2024 1100", details[1]);
        assertEquals("01/01/2024 1300", details[2]);
    }
}
