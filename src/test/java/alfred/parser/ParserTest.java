package alfred.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testGetTaskNumberFromInput_ValidInput() {
        String input = "mark 2";
        int taskNumber = Parser.getTaskNumberFromInput(input);
        assertEquals(2, taskNumber);
    }

    @Test
    void testGetTaskNumberFromInput_InvalidInput() {
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
    void testIsValidCommand_ValidInput() {
        String input = "mark 2";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertTrue(isValid);
    }

    @Test
    void testIsValidCommand_InvalidCommandFormat() {
        String input = "mark2";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testIsValidCommand_InvalidTaskNumber_TooHigh() {
        String input = "mark 5";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testIsValidCommand_InvalidTaskNumber_TooLow() {
        String input = "mark 0";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }

    @Test
    void testIsValidCommand_InvalidTaskNumber_Negative() {
        String input = "mark -1";
        boolean isValid = Parser.isValidCommand(input, "mark", 3);
        assertFalse(isValid);
    }
}

