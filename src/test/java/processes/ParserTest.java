package processes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void checkValidIndex_validInput_success() {
        Parser pc = new Parser();
        assertTrue(pc.checkValidIndex("5 "));
        assertTrue(pc.checkValidIndex("  57 "));
    }

    @Test
    public void checkValidIndex_invalidInput_success() {
        Parser pc = new Parser();
        assertFalse(pc.checkValidIndex("helloWorld"));
        assertFalse(pc.checkValidIndex("test 57 test"));
    }
}
