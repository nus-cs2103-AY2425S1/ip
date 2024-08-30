package bob.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ByeCommandTest {
    @Test
    public void IsRunningTest() {
        ByeCommand byeCommand = new ByeCommand();
        assertFalse(byeCommand.isRunning());
    }
}
