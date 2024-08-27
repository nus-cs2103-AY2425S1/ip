package vuewee.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import vuewee.EndProgramException;

public class ByeCommandTest {

    @Test
    public void testByeCommand() {
        ByeCommand byeCommand = new ByeCommand();
        assertThrows(EndProgramException.class, () -> byeCommand.execute(null, null, null));
    }
}