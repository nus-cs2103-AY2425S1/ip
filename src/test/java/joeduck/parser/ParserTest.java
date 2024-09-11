package joeduck.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import joeduck.command.Command;
import joeduck.exception.InvalidCommandException;
public class ParserTest {
    @Test
    public void parseUserInput_validTodo_success() throws InvalidCommandException {
        String testInput = "todo asdf";
        Command res = new Parser().parseUserInput(testInput);
        assertEquals("todo", res.command());
        assertEquals("asdf", res.args());
    }

    @Test
    public void parseUserInput_emptyTodo_success() throws InvalidCommandException {
        String testInput = "todo";
        Command res = new Parser().parseUserInput(testInput);
        assertEquals("todo", res.command());
        assertEquals("", res.args());
    }

    @Test
    public void parseUserInput_numberInput_throwsException() {
        String testInput = "123123";
        try {
            Command res = new Parser().parseUserInput(testInput);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid command!", e.getMessage());
        }
    }

    @Test
    public void parseUserInput_whitespaceInput_throwsException() {
        String testInput = "    ";
        try {
            Command res = new Parser().parseUserInput(testInput);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid command!", e.getMessage());
        }
    }
}
