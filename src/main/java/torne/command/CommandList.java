package torne.command;

import java.util.LinkedHashMap;

/**
 * Encapsulates the list of commands used by a particular instance of the {@link Parser}.
 */
public class CommandList {
    private final LinkedHashMap<String, Command> commandLinkedHashMap = new LinkedHashMap<>();

    protected CommandList() {
        // initialise commands
        addCommand(new Bye());
        addCommand(new Help());
        addCommand(new ListTasks());
        addCommand(new AddTaskTodo());
        addCommand(new AddTaskDeadline());
        addCommand(new AddTaskEvent());
        addCommand(new DeleteTask());
        addCommand(new FindTask());
        addCommand(new MarkTask());
        addCommand(new UnmarkTask());
    }

    private void addCommand(Command cmd) {
        commandLinkedHashMap.put(cmd.getName().toLowerCase(), cmd);
    }

    public LinkedHashMap<String, Command> getCommands() {
        return commandLinkedHashMap;
    }
}
