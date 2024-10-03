package duck.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Tests the Parser class.
 */
class ParserTest {

    /**
     * Tests the parse method of the Parser class with valid bye command input.
     */
    @Test
    public void parse_byeCommand_success() {
        String input1 = "bye";
        String input2 = "Bye ";
        String input3 = "bYe  ";
        String input4 = "byE  \t";
        String input5 = "   BYE    ";

        assertTrue(Parser.parse(input1).isExit());
        assertTrue(Parser.parse(input2).isExit());
        assertTrue(Parser.parse(input3).isExit());
        assertTrue(Parser.parse(input4).isExit());
        assertTrue(Parser.parse(input5).isExit());
    }
}
