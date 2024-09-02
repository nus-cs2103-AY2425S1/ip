package vecrosen;

import org.junit.jupiter.api.Test;

import javax.management.ObjectName;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    /**
     * Tests whether the code can mark and unmark tasks accurately.
     */
    @Test
    public void markUnmarkTest() {
        ArrayList<Object> args = new ArrayList<Object>();
        assertEquals(Parser.ActionType.mark, Parser.parse("mark 6", args));
        assertEquals(6, (Integer) args.get(0));
        args.clear();
        assertEquals(Parser.ActionType.unmark, Parser.parse("unmark 3", args));
        assertEquals(3, (Integer) args.get(0));
        args.clear();
        assertEquals(Parser.ActionType.formatting, Parser.parse("unmark ", args));
        assertEquals(Parser.ActionType.formatting, Parser.parse("mark", args));
        assertEquals(Parser.ActionType.formatting, Parser.parse("unmark abc", args));
        args.clear();
    }
}
