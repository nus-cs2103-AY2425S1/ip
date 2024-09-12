package gray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_badCommand_exceptionThrown() {
        try {
            Parser.parse("Hello World");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            Parser.parse("");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            Parser.parse(" ");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        try {
            Parser.parse("deadline return book /by ");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! The description and deadline cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("event project meeting /from  /to ");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! The description, start and end cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("todo ");
            fail();
        } catch (GrayException e) {
            assertEquals("OOPS!!! The description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_badDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book /by some date");
            fail();
        } catch (GrayException e) {
            assertEquals("Cannot parse datetime yyyy-MM-dd HHmm", e.getMessage());
        }

        try {
            Parser.parse("event project meeting /from now /to then");
            fail();
        } catch (GrayException e) {
            assertEquals("Cannot parse datetime yyyy-MM-dd HHmm", e.getMessage());
        }
    }
}
