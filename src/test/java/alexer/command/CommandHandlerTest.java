package alexer.command;

import alexer.ui.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandHandlerTest {
    @Test
    public void getCommand_commandNotFound() {
        String commandName = "klsdajklsjdkajsd"; // some command that will not exist
        CommandHandler commandHandler = new CommandHandler();
        assertNull(commandHandler.getCommand(commandName));
    }

    @Test
    public void createCommand_commandFound() {
        class TestCommand extends Command {
            public TestCommand() {
                super("test");
            }

            @Override
            public Response run(String... arguments) {
                return new Response("test");
            }
        }

        CommandHandler handler = new CommandHandler();
        handler.registerCommand(new TestCommand());
        assertNotNull(handler.getCommand("test"));
    }
}
