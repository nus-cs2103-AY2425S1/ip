package parse;

import exception.MissingArg;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    @Test
    public void parseTodo_test() throws MissingArg {
        assertEquals("read book", Parse.parseTodo("todo read book"));
    }
}
