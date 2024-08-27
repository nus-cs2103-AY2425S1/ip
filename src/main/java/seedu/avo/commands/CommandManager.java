package seedu.avo.commands;
import seedu.avo.storage.FileStorage;
import seedu.avo.storage.TaskParser;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;

import java.util.Map;
import java.util.HashMap;

public class CommandManager {
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

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
        commandMap.put(CommandName.EXIT.getName(), ExitCommand.of());
        unknownCommand = UnknownCommand.of();
    }
    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, unknownCommand);
    }
}
