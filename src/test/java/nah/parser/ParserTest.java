package nah.parser;
import nah.command.Command;
import nah.exceptions.NahException;
import nah.storage.Storage;
import nah.tasklist.TaskList;
import nah.ui.UI;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test ()
    public void exceptionTest1() {
        try {
            Command cmd = Parser.parse("todo");
            fail("Expected NahException");

        } catch (NahException e) {
            assertEquals(e.getMessage(), " Nahhhh!!! Todo needs description\n");
        }
    }
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

    @Test
    public void commandTypeReturnTest1() {

        try {
            Command cmd = Parser.parse("Deadline homework /by 2025-12-12 2000");
            assertTrue(cmd instanceof Command.AddCommand);

        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

    @Test
    public void commandTypeReturnTest2() {
        try {
            Command cmd = Parser.parse("Nahannahnahhhan");
            assertTrue(cmd instanceof Command.UnknownCommand);

        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

    @Test
    public void commandTypeReturnTest3() {
        try {
            Command cmd = Parser.parse("list asnhdjkasasdb as");
            fail("NahException expected");

        } catch (NahException e) {
            assertEquals(" Nahh!!! Do not type nonsense after 'list' command\n",e.getMessage());
        }
    }
}

