package alice.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ParserTest {
    @Test
    public void parseDescriptionTest() {
        String expected = "expected description";
        String input = String.format("filler %s /filler filler", expected);
        String description = Parser.parseDescription(input);
        assertEquals(expected, description);
    }

    @Test
    public void parseInvalidDescriptionTest() {
        String input = "filler";
        assertThrowsExactly(
            IllegalArgumentException.class,
            () -> Parser.parseDescription(input)
        );
    }
}