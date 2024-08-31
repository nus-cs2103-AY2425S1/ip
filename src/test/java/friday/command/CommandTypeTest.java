package friday.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandTypeTest {

    @Test
    void parseValidCommand() {
        Assertions.assertEquals(CommandType.TODO, CommandType.valueOf("todo".toUpperCase()));
    }

    @Test
    void parseInvalidCommand_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CommandType.valueOf("invalidcommand");
        });
    }
}
