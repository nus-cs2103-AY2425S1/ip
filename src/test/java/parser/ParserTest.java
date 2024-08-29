package parser;
import command.ExitCommand;
import command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void testParseListCommand(){
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
    }

    @Test
    public void testParseExitCommand(){
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
    }

}
