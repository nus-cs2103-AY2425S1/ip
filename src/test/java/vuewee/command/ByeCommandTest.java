package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vuewee.EndProgramException;

public class ByeCommandTest {

    @Test
    public void testByeCommand() {
        ByeCommand byeCommand = new ByeCommand();
        assertThrows(EndProgramException.class, () -> byeCommand.executeCommand(null, null, null));
    }
}
