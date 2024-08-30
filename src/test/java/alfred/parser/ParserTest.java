package alfred.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void getTaskNumber_validInput() {
        String input = "mark 2";
        int taskNumber = Parser.getTaskNumberFromInput(input);
        assertEquals(2, taskNumber);
    }

    @Test
    void getTaskNumber_invalidInput() {
        String input = "mark";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Parser.getTaskNumberFromInput(input);
        });
    }

    @Test
    void testGetCommand() {
        String input = "mark 2";
        String command = Parser.getCommand(input);
        assertEquals("mark", command);
    }

    @Test
    void isValidCommand_validInput() {
        String input = "mark 2";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertTrue(isValid);
    }

    @Test
    void isValidCommand_invalidCommandFormat() {
        String input = "mark2";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testInvalidTaskNumber_tooHigh() {
        String input = "mark 5";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testInvalidTaskNumber_tooLow() {
        String input = "mark 0";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testInvalidTaskNumber_negative() {
        String input = "mark -1";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }
}

