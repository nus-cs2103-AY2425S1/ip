package TrackBot.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    ExitCommand exitCommand = new ExitCommand();
    @Test
    public void test_exit() {
        assertTrue(exitCommand.isExit());
    }

}
