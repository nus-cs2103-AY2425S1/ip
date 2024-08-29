package sunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void test1() {
        Parser x = new Parser("todo eat lunch");
        assertEquals("todo", x.getCommand());
        assertEquals("eat lunch", x.getMessage());
    }
}
