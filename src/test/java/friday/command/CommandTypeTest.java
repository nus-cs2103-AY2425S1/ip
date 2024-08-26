package friday.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

    @Test
    void parseValidCommand() {
        assertEquals(CommandType.TODO, CommandType.valueOf("todo".toUpperCase()));
    }

    @Test
    void parseInvalidCommand_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            CommandType.valueOf("invalidcommand");
        });
    }
}