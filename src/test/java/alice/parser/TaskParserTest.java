package alice.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class TaskParserTest {
    @Test
    public void parseDescriptionTest() {
        String expected = "expected description";
        String input = String.format("filler %s /filler filler", expected);
        String description = TaskParser.parseDescription(input);
        assertEquals(expected, description);
    }

    @Test
    public void parseInvalidDescriptionTest() {
        String input = "filler";
        assertThrowsExactly(IllegalArgumentException.class, () -> TaskParser.parseDescription(input));
    }
}
