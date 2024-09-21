package alexer.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the command handler that handles user input (i.e. commands)
 * and manages all the commands in the chatbot (sort of like a command manager)
 *
 * @author sayomaki
 */
public class CommandHandler {
    private final Map<String, Command> commands;

    /**
     * Creates the command handler for the chatbot
     */
    public CommandHandler() {
        commands = new HashMap<>();

        registerAllCommands();
    }

    /**
     * Registers a command with the chatbot.
     * Most commands should be automatically registered on creation
     * in the registerAllCommands() method, but for any exceptions they can
     * be registered manually using this command.
     *
     * @param command The command to be registered
     */
    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Returns the command with the given name if it exists,
     * otherwise it will return null
     *
     * @param command the name of the command to return
     * @return The command if found, otherwise null
     */
    public Command getCommand(String command) {
        return commands.get(command);
    }

    /**
     * Registers all the commands for the chatbot.
     */
    private void registerAllCommands() {
        registerCommand(new ListTaskCommand());
        registerCommand(new FindTaskCommand());
        registerCommand(new DeleteTaskCommand());
        registerCommand(new MarkTaskCommand());
        registerCommand(new UnmarkTaskCommand());
        registerCommand(new AddTodoCommand());
        registerCommand(new AddDeadlineCommand());
        registerCommand(new AddEventCommand());

        // ensure that we have command registered properly
        assert !commands.isEmpty();
    }
}
