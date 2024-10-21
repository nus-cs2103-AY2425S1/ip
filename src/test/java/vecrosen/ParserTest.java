package vecrosen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ParserTest {
    /**
     * Tests whether the code can mark and unmark tasks accurately.
     */
    @Test
    public void markUnmarkTest() {
        ArrayList<Object> args = new ArrayList<Object>();
        assertEquals(Parser.ActionType.MARK, Parser.parse("mark 6", args));
        assertEquals(6, (Integer) args.get(0));
        args.clear();
        assertEquals(Parser.ActionType.UNMARK, Parser.parse("unmark 3", args));
        assertEquals(3, (Integer) args.get(0));
        args.clear();
        assertEquals(Parser.ActionType.FORMATTING, Parser.parse("unmark ", args));
        assertEquals(Parser.ActionType.FORMATTING, Parser.parse("mark", args));
        assertEquals(Parser.ActionType.FORMATTING, Parser.parse("unmark abc", args));
        args.clear();
    }
}
