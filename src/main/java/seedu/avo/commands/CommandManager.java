package seedu.avo.commands;

import java.util.HashMap;
import java.util.Map;

import seedu.avo.tasks.TaskManager;
/**
 * Represents a helper class to map commands to instances of Command
 */
public class CommandManager {
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    /**
     * @param taskManager The controller to perform tasks related jobs
     */
    public CommandManager(TaskManager taskManager) {
        commandMap = new HashMap<String, Command>();
        commandMap.put(CommandName.LIST.getName(), new ListCommand(taskManager));
        commandMap.put(CommandName.MARK.getName(), new MarkCommand(taskManager));
        commandMap.put(CommandName.UNMARK.getName(), new UnmarkCommand(taskManager));
        commandMap.put(CommandName.TODO.getName(), new TodoCommand(taskManager));
        commandMap.put(CommandName.DEADLINE.getName(), new DeadlineCommand(taskManager));
        commandMap.put(CommandName.EVENT.getName(), new EventCommand(taskManager));
        commandMap.put(CommandName.DELETE.getName(), new DeleteCommand(taskManager));
        commandMap.put(CommandName.SEARCH_DATE.getName(), new SearchDateCommand(taskManager));
        commandMap.put(CommandName.SEARCH_NAME.getName(), new SearchNameCommand(taskManager));
        commandMap.put(CommandName.HELP.getName(), HelpCommand.of());
        unknownCommand = UnknownCommand.of();
    }

    /**
     * Returns a Command that corresponds to the identifier in the key-value pair,
     * defaults to UnknownCommand
     * @param identifier A key in the key-value pair
     * @return A Command with the identifier as a key
     */
    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, unknownCommand);
    }
}
