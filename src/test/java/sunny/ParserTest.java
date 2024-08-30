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
        assertEquals("todo", x.getCommand());
        assertEquals("eat lunch", x.getMessage());

        Parser y = new Parser("Hello");
        assertEquals("Hello", y.getCommand());
        assertNull(y.getMessage());

        Parser z = new Parser("");
        assertEquals("", z.getCommand());
        assertNull(z.getMessage());
    }
}
