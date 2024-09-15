package patrick.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseTask_listCommand() {
        Parser parser = new Parser();
        String response = parser.parseTask("list");
        assertTrue(response.contains("Here are the tasks in your list:"));
    }

    @Test
    public void testParseTask_byeCommand() {
        Parser parser = new Parser();
        String response = parser.parseTask("bye");
        assertEquals("BYE", response);
    }

    @Test
    public void testParseTask_invalidCommand() {
        Parser parser = new Parser();
        String response = parser.parseTask("invalidCommand");
        assertTrue(response.contains("Watch your words"));
    }
}
