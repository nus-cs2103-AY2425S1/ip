package poChat.bot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = Parser.of(new TaskList());
        try {
            assertTrue(parser.parse("bye"));
            assertFalse(parser.parse("todo something"));
            assertFalse(parser.parse("todo"));
            assertFalse(parser.parse("deadline something /by sometime"));
            assertFalse(parser.parse("event something /from start /to end"));
            assertFalse(parser.parse("hello"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
