package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.AddTodoCommand;
import command.Command;
import exceptions.DelphiException;
import exceptions.InvalidInputException;

/**
 * test class to test the parser class
 */
public class ParserTest {
    private final Parser p = new Parser();

    @Test
    public void testParserValidTodoCommand_exceptionThrown() {
        try {
            Command command = p.parseInput("todo read book");
            assertInstanceOf(AddTodoCommand.class, command);
        } catch (DelphiException e) {
            fail();
        }
    }

    @Test
    public void checkStringPrefix() {

        assertEquals(true, p.checkStringPrefix("Event", 5, "Event"));

        assertEquals(true, p.checkStringPrefix("Hello world", 5, "Hello"));

        assertEquals(false, p.checkStringPrefix("Hallo world", 5, "Hello"));

        assertEquals(false, p.checkStringPrefix("Hello world", 5, ""));

    }

    /**
     * Tests the {@code parseDeadline} method to ensure it handles invalid inputs and throws
     * the appropriate exception when the input is not formatted correctly.
     *
     * <p>Test cases include:
     * <ul>
     *   <li>Valid input: "submit essay /by tomorrow" - checks for correct extraction of the task and deadline.</li>
     *   <li>Invalid input: "submit essay /bytomorrow" - missing space after "/by",
     *      should throw {@code InvalidInputException}.</li>
     *   <li>Invalid input: "submit essay/by tomorrow" - checks if spaces are handled correctly.</li>
     *   <li>Invalid input: "submit essay by 20/09/2024 2359" - missing "/by",
     *      should throw {@code InvalidInputException}.</li>
     * </ul>
     *
     * <p>It validates that the correct task and deadline parts are returned for valid inputs and that
     * the exception message matches the expected error when an {@code InvalidInputException} is thrown.
     *
     * <p>The expected exception message is: "the input you have provided me is not formatted correctly."
     */
    @Test
    public void parseDeadline_exceptionThrown() {
        String x = "submit essay /by tomorrow";
        String y = "submit essay /bytomorrow";
        String w = "submit essay/by       tomorrow";
        String z = "submit essay by 20/09/2024 2359";
        try {
            assertEquals("submit essay", p.parseDeadline(x)[0]);

            assertEquals("(by: tomorrow)", p.parseDeadline(x)[1]);

            assertEquals("submit essay", p.parseDeadline(y)[0]);

            assertEquals("(by: tomorrow)", p.parseDeadline(y)[1]);

            assertEquals("submit essay", p.parseDeadline(w)[0]);

            assertEquals("submit essay", p.parseDeadline(w)[0]);

            assertEquals("submit essay", p.parseDeadline(z)[0]);

            fail();
        } catch (InvalidInputException e) {
            assertEquals("the input you have provided me is not formatted correctly.", e.getMessage());
        }
    }
}
