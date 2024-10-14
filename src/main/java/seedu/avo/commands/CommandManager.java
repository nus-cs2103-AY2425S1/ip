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
        commandMap.put(CommandName.LIST.getName(), ListCommand.of(taskManager));
        commandMap.put(CommandName.MARK.getName(), MarkCommand.of(taskManager));
        commandMap.put(CommandName.UNMARK.getName(), UnmarkCommand.of(taskManager));
        commandMap.put(CommandName.TODO.getName(), TodoCommand.of(taskManager));
        commandMap.put(CommandName.DEADLINE.getName(), DeadlineCommand.of(taskManager));
        commandMap.put(CommandName.EVENT.getName(), EventCommand.of(taskManager));
        commandMap.put(CommandName.DELETE.getName(), DeleteCommand.of(taskManager));
        commandMap.put(CommandName.SEARCH_DATE.getName(), SearchDateCommand.of(taskManager));
        commandMap.put(CommandName.SEARCH_NAME.getName(), SearchNameCommand.of(taskManager));
        commandMap.put(CommandName.HELP.getName(), HelpCommand.of());
        commandMap.put(CommandName.EXIT.getName(), ExitCommand.of());
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
