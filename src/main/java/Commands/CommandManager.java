package Commands;
import Storage.FileStorage;
import Tasks.TaskManager;

import java.util.Map;
import java.util.HashMap;

public class CommandManager {
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;
    public CommandManager() {
        TaskManager manager = new TaskManager(new FileStorage("data"));
        commandMap = new HashMap<String, Command>();
        commandMap.put(CommandName.LIST.getName(), new ListCommand(manager));
        commandMap.put(CommandName.MARK.getName(), new MarkCommand(manager));
        commandMap.put(CommandName.UNMARK.getName(), new UnmarkCommand(manager));
        commandMap.put(CommandName.TODO.getName(), new TodoCommand(manager));
        commandMap.put(CommandName.DEADLINE.getName(), new DeadlineCommand(manager));
        commandMap.put(CommandName.EVENT.getName(), new EventCommand(manager));
        commandMap.put(CommandName.DELETE.getName(), new DeleteCommand(manager));
        commandMap.put(CommandName.SEARCH_DATE.getName(), new SearchDateCommand(manager));
        commandMap.put(CommandName.EXIT.getName(), new ExitCommand());
        unknownCommand = new UnknownCommand();
    }
    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, unknownCommand);
    }
}
