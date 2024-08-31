package processes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void checkValidIndex_validInput_success() {
        Parser pc = new Parser();
        assertTrue(pc.checkValidIndex("mark 5 ", 5));
        assertTrue(pc.checkValidIndex("unmark 5 ", 7));
    }

    @Test
    public void checkValidIndex_invalidInput_success() {
        Parser pc = new Parser();
        assertFalse(pc.checkValidIndex("marking 5 ", 5));
        assertFalse(pc.checkValidIndex("mark 5 ", 7));
    }
}
