package sunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests the parser class
 */
public class ParserTest {
    /**
     * Test if the parser gets the command, message, and null case right
     */
    @Test
    public void test1() {
        Parser x = new Parser("todo eat lunch");
        assertEquals("todo", x.getFirstHalf());
        assertEquals("eat lunch", x.getSecondHalf());

        Parser y = new Parser("Hello");
        assertEquals("Hello", y.getFirstHalf());
        assertNull(y.getSecondHalf());

        Parser z = new Parser("");
        assertEquals("", z.getFirstHalf());
        assertNull(z.getSecondHalf());
    }
}
