package TrackBot.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    ExitCommand exitCommand = new ExitCommand();
    @Test
    public void testCanExit() {
        assertEquals(true, exitCommand.isExit());
    }

}
