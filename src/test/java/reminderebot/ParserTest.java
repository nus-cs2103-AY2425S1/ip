package reminderebot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for Parser class.
 */
public class ParserTest {
    /**
     * Test if the parse method works as intended.
     * @throws Exception
     */
    @Test
    public void invalidCommandArgs() throws Exception {
        // Test that invalid input will throw the correct Reminderebot exception
        String line = "abcd";
        ReminderebotException exception = assertThrows(ReminderebotException.class, () -> Parser.parse(line));
        assertEquals("Oops! I'm sorry, but I don't know what that means. :(\n" +
                "Please enter a command below:\n" +
                " bye\n list\n mark <int>\n unmark <int>\n todo <taskname>\n" +
                " deadline <taskname> /by <duedate>\n event <name> /from <datetime> /to <datetime>",
                exception.getMessage());
    }
}