import tomo.Parser;
import exception.ParserException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    public void testParser() {
        Parser parser = new Parser();
        String cmd = "event MAP /from 2024-09-14 1700 /to 2024-09-14 1900";
        try {
            String[] args = parser.parse(cmd);
            assertEquals(4, args.length);
            assertEquals("event", args[0]);
            assertEquals("MAP", args[1]);
            assertEquals("2024-09-14 1700", args[2]);
            assertEquals("2024-09-14 1900", args[3]);
        } catch (ParserException e) {
            throw new AssertionError("The test should not throw an exception");
        }
    }
}
