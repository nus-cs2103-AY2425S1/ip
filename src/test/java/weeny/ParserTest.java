package weeny;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testExtractFirstWord() {
        Parser parser = new Parser();
        String input = "todo read book";
        String command = parser.extractFirstWord(input);
        assertEquals("todo", command);
    }

    @Test
    public void testExtractFirstWordWithLeadingSpaces() {
        Parser parser = new Parser();
        String input = "event meeting /from 2024/08/30 1400 /to 2024/08/31 1800";
        String command = parser.extractFirstWord(input);
        assertEquals("event", command);
    }

    @Test
    public void testExtractFirstWordEmptyString() {
        Parser parser = new Parser();
        String input = "";
        String command = parser.extractFirstWord(input);
        assertEquals("", command);
    }
}

