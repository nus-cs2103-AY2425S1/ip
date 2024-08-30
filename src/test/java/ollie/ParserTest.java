package ollie;

import ollie.command.AddCommand;
import ollie.exception.OllieException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_NotRecognisedCommand_exceptionThrown() {
        try {
            Parser.parse("Not A Valid Command");
            fail(); // Should not reach this line
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",e.getMessage());
        }
    }
    @Test
    public void parse_correctInputToCreateDeadline_success() {
        try {
            assertTrue(Parser.parse("deadline Science Homework /by 2024-01-01") instanceof AddCommand);
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            fail("OllieException should not be thrown");
        }
    }

    @Test
    public void parse_missingByInInput_exceptionThrown() {
        try {
            assertTrue(Parser.parse("deadline Science Homework") instanceof AddCommand);
            fail(); // Should not reach this line
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            assertEquals("OOPS!!! Use deadline with a \"/by\" keyword and a date/time.",e.getMessage());
        }
    }

    @Test
    public void parse_emptyByInInput_exceptionThrown() {
        try {
            assertTrue(Parser.parse("deadline Science Homework /by ") instanceof AddCommand);
            fail(); // Should not reach this line
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            assertEquals("OOPS!!! Date/Time of deadline cannot be empty!",e.getMessage());
        }
    }

    @Test
    public void parse_emptyDescInInput_exceptionThrown() {
        try {
            assertTrue(Parser.parse("deadline /by 2024-01-01") instanceof AddCommand);
            fail(); // Should not reach this line
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            assertEquals("OOPS!!! Description of deadline cannot be empty!",e.getMessage());
        }
    }

    @Test
    public void parse_wronglyFormattedDateInInput_exceptionThrown() {
        try {
            assertTrue(Parser.parse("deadline Science Homework /by 01-01-2024") instanceof AddCommand);
            fail(); // Should not reach this line
        } catch (OllieException e) {
            // Fail the test if an exception is thrown
            assertEquals("OOPS!!! Date must be valid and strictly formatted as yyyy-mm-dd !",e.getMessage());
        }
    }
}
