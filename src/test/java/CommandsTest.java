import ip.derrick.Commands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {

    @Test
    public void testCorrectCommand() {
        Commands result = Commands.fromString("Deadline");
        assertEquals(Commands.DEADLINE, result);
    }

    @Test
    public void testIncorrectCommand() {
        Commands result = Commands.fromString("Execute");
        assertEquals(Commands.UNKNOWN, result);
    }

    @Test
    public void testEmptyCommand() {
        Commands result = Commands.fromString("");
        assertEquals(Commands.UNKNOWN, result);
    }
}
