package hana;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_todoCommand_returnsAddCommand() throws HanaException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_invalidCommand_throwsHanaException() {
        assertThrows(HanaException.class, () -> Parser.parse("invalid command"));
    }
}

