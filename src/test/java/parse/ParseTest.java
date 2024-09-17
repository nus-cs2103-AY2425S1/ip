package parse;

import exception.MissingArg;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class ParseTest {
    @Test
    public void parseTodo_validInput_returnsDescription() throws MissingArg {
        assertEquals("read book", Parse.parseTodo("todo read book"));
        assertEquals("borrow book", Parse.parseTodo("todo borrow book"));
    }
    @Test
    public void parseTodo_missingArgument_throwsMissingArg() {
        assertThrows(MissingArg.class, () -> Parse.parseTodo("todo"));
        assertThrows(MissingArg.class, () -> Parse.parseTodo("todo  "));
    }
}
