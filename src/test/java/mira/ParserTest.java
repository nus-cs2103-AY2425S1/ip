package mira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    /**
     * Tests that `parse` throws `NumberFormatException` when a non-integer is provided for commands
     * like `mark`, `unmark`, and `delete`.
     */
    @Test
    public void testParseInt_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> {
            Parser.parse("mark abc");
        });
    }

    /**
     * Tests that `parse` throws `MiraException` when an unknown command is provided.
     */
    @Test
    public void testParse_unknownCommand_throwsMiraException() {
        MiraException exception = assertThrows(MiraException.class, () -> {
            Parser.parse("unknownCommand");
        });
        assertEquals("I'm sorry, I don't understand that command.", exception.getMessage());
    }

    /**
     * Tests that `parse` throws `MiraException` when the `deadline` command is provided without
     * the required `/by` format.
     */
    @Test
    public void testParse_deadlineWithoutBy_throwsMiraException() {
        MiraException exception = assertThrows(MiraException.class, () -> {
            Parser.parse("deadline submit assignment");
        });
        assertEquals("Invalid format. Use: deadline <description> /by <deadline>",
                exception.getMessage());
    }

    /**
     * Tests that `parse` throws `MiraException` when the `event` command is provided without
     * the required `/to` format.
     */
    @Test
    public void testParse_eventWithoutTo_throwsMiraException() {
        MiraException exception = assertThrows(MiraException.class, () -> {
            Parser.parse("event party /from Monday");
        });
        assertEquals("Invalid format. Use: event <description> /from <start> /to <end>",
                exception.getMessage());
    }
}
