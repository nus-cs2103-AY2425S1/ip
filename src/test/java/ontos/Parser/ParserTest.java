package ontos.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import ontos.commands.Command;
import ontos.exception.OntosException;
import ontos.parser.Parser;

public class ParserTest {
    @Test
    void parse_byeInput_byeCommandReturned() throws OntosException {
        assertEquals(new Command.ByeCommand(), Parser.parse("bye"));
    }

    @Test
    void parse_listInput_listCommandReturned() throws OntosException {
        assertEquals(new Command.ListCommand(), Parser.parse("list"));
    }

    @Test
    void parse_invalidInput_exceptionThrown() {
        try {
            assertEquals(new Command.ByeCommand(), Parser.parse("invalid command"));
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals(null, e.getMessage());
        } catch (OntosException e) {
            fail();
        }
    }

    @Test
    void parse_invalidMarkInput_exceptionThrown() {
        try {
            assertEquals(new Command.MarkCommand(false, 0), Parser.parse("mark"));
        } catch (OntosException e) {
            assertEquals("The correct usage of 'mark' is: mark n, where n is a natural number (ℕ).", e.getMessage());
        }
    }
}
