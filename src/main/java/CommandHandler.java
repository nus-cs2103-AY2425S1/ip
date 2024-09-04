import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<>();

        registerAllCommands();
    }

    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

    private void registerAllCommands() {
        registerCommand(new ListTaskCommand());
    }
}
