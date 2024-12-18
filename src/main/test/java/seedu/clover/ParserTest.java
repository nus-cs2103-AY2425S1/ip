import barry.util.Parser;
import barry.util.Parser.Commands;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseCommand() {
        Commands command = Parser.parse("todo read book");
        assertEquals(Commands.TODO, command, "Command should be parsed as TODO.");
    }
}
