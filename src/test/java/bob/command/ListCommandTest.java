package bob.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandTest {
    @Test
    public void IsRunningTest() {
        ListCommand listCommand = new ListCommand();
        assertTrue(listCommand.isRunning());
    }
}
