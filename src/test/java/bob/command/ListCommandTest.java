package bob.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void isRunningTest() {
        ListCommand listCommand = new ListCommand();
        assertTrue(listCommand.isRunning());
    }
}
