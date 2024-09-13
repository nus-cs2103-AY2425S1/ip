package jeff.command;

import java.util.HashMap;
import java.util.Map;

import jeff.exceptions.JeffException;

/**
 * CommandFactory is responsible for creating Command objects based on user input.
 * It uses a map to associate command keywords with the corresponding Command objects.
 * This factory pattern decouples the command creation logic from the Parser, making it more scalable and maintainable.
 */
public class CommandFactory {
    private final Map<String, CommandCreator> commandMap = new HashMap<>();

    /**
     * Constructs the CommandFactory and registers all available commands in the command map.
     * Each command is associated with its respective keyword and creation logic.
     */
    public CommandFactory() {
        // Register all commands here
        commandMap.put("bye", args -> new ExitCommand());
        commandMap.put("list", args -> new ListCommand());
        commandMap.put("print", PrintCommand::new);
        commandMap.put("mark", MarkCommand::new);
        commandMap.put("unmark", UnMarkCommand::new);
        commandMap.put("delete", DeleteCommand::new);
        commandMap.put("todo", ToDoCommand::new);
        commandMap.put("deadline", DeadlineCommand::new);
        commandMap.put("event", EventCommand::new);
        commandMap.put("find", FindCommand::new);
        commandMap.put("edit", EditCommand::new);
    }

    public Command getCommand(String commandWord, String args) throws JeffException {
        CommandCreator creator = commandMap.get(commandWord.toLowerCase());
        if (creator == null) {
            throw new JeffException("Unknown command!");
        }
        return creator.create(args);
    }

    // Functional interface for creating commands
    @FunctionalInterface
    private interface CommandCreator {
        Command create(String args) throws JeffException;
    }
}
