package yap.ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static yap.ui.Parser.parseInputAsToDo;

public class ParserTest {

    @Test
    public void parseInputAsToDo_success() throws InputException {
        assertEquals("[T][ ] task1", parseInputAsToDo("todo task1").toString());
    }

    @Test
    public void parseInputAsToDo_fail() {
        try {
            parseInputAsToDo("todo ");
            fail();
        } catch (InputException exception) {
            assertEquals("The todo has no name!", exception.getMessage());
        }
    }
}
