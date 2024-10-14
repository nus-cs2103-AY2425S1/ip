package seedu.avo.commands;

import seedu.avo.tasks.TaskManager;
/**
 * Represents the command to list tasks
 */
public class ListCommand extends Command {
    private static ListCommand instance;
    private final TaskManager manager;
    private ListCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of ListCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of ListCommand
     */
    public static ListCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new ListCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) {
        String message = manager.listTasks();
        return new CommandResult(message, false);
    }
}
