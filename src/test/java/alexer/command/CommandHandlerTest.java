package alexer.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandHandlerTest {
    @Test
    public void getCommand_commandNotFound() {
        String commandName = "klsdajklsjdkajsd"; // some command that will not exist
        CommandHandler commandHandler = new CommandHandler();
        assertNull(commandHandler.getCommand(commandName));
    }
}
