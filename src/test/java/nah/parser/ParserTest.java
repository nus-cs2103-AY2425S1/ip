package nah.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nah.command.Command;
import nah.exceptions.NahException;
public class ParserTest {
    /**
     * Tests if method parse throw correct exception.
     */
    @Test
    public void exceptionTest1() {
        try {
            Command cmd = Parser.parse("todo");
            fail("Expected NahException");

        } catch (NahException e) {
            assertEquals(e.getMessage(), " Nahhhh!!! Todo needs description\n");
        }
    }

    /**
     * Tests if method parse throw correct exception.
     */
    @Test
    public void exceptionTest2() {
        try {
            Command cmd = Parser.parse("Deadline homework /by 2025/12/12 2000");
            fail("Expected NahException");

        } catch (NahException e) {
            assertEquals(e.getMessage(),
                    " Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
        }
    }

    /**
     * Tests of method parse throws correct exception.
     */
    @Test
    public void exceptionTest3() {
        try {
            Command cmd = Parser.parse("list asnhdjkasasdb as");
            fail("NahException expected");

        } catch (NahException e) {
            assertEquals(" Nahh!!! Do not type nonsense after 'list' command\n",
                    e.getMessage());
        }
    }

    /**
     * Tests of method parse return object of type Command.AddCommand.
     */
    @Test
    public void commandTypeReturnTest1() {

        try {
            Command cmd = Parser.parse("Deadline homework /by 2025-12-12 2000");
            assertTrue(cmd instanceof Command.AddCommand);

        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

    /**
     * Tests of method parse return object of type Unknown.AddCommand.
     */
    @Test
    public void commandTypeReturnTest2() {
        try {
            Command cmd = Parser.parse("Nahannahnahhhan");
            assertTrue(cmd instanceof Command.UnknownCommand);

        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

}

