package commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExit_allScenarios_true() {
        ByeCommand command = new ByeCommand();
        assertTrue(command.isExit());
    }
}
