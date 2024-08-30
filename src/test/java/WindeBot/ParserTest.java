package Winde;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void ParserTest() {
        Parser parser = null;
        try {
            assertTrue(parser.parse("bye").execute());
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
