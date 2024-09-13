package katheryne;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testIsValidDate() {
        assertEquals(true, Parser.isValidDate("2024-09-12"));
    }

}
