package bob.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void isRunningTest() {
        ByeCommand byeCommand = new ByeCommand();
        assertFalse(byeCommand.isRunning());
    }
}
