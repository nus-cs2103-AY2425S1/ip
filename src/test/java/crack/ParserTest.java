package crack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testParseTodoDescription() {
        String input = "todo read book";
        String expectedDescription = "read book";

        String result = Parser.parseTodoDescription(input);

        // Check if the correct description is parsed
        assertEquals(expectedDescription, result);
    }

    @Test
    void testParseTaskNumber() {
        String input = "mark 2";
        int expectedTaskNumber = 1; // 1-based index is converted to 0-based

        int result = Parser.parseTaskNumber(input);

        // Check if the correct task number is parsed
        assertEquals(expectedTaskNumber, result);
    }
}
