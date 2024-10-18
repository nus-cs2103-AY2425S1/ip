package alfred.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import alfred.ui.AlfredResponse;

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
    void validateCommand_validInput() {
        String input = "mark 2";
        String validationResult = Parser.validateCommand(input, "mark", 3);
        assertTrue(validationResult.isEmpty(), "Expected command to be valid");
    }

    @Test
    void validateCommand_invalidCommandFormat() {
        String input = "mark2";
        String validationResult = Parser.validateCommand(input, "mark", 3);
        assertEquals(AlfredResponse.showInvalidCommandFormat(),
                validationResult, "Expected invalid command format error message");
    }

    @Test
    void validateCommand_taskNumberTooHigh() {
        String input = "mark 5";
        String validationResult = Parser.validateCommand(input, "mark", 3);
        assertEquals(AlfredResponse.showInvalidTaskNumber(3),
                validationResult, "Expected invalid task number error message (too high)");
    }

    @Test
    void validateCommand_taskNumberTooLow() {
        String input = "mark 0";
        String validationResult = Parser.validateCommand(input, "mark", 3);
        assertEquals(AlfredResponse.showInvalidTaskNumber(3),
                validationResult, "Expected invalid task number error message (too low)");
    }

    @Test
    void validateCommand_negativeTaskNumber() {
        String input = "mark -1";
        String validationResult = Parser.validateCommand(input, "mark", 3);
        assertEquals(AlfredResponse.showInvalidCommandFormat(),
                validationResult, "Expected invalid task number error message (negative)");
    }
}

