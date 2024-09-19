package nixy.command;

import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Class representing the command to find tasks.
 */
public class FindCommand implements Command {
    private Ui ui;
    private TaskList tasks;
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param ui The Ui object to interact with the user.
     * @param tasks The TaskList object to store tasks.
     * @param keyword The keyword to search for.
     */
    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        this.ui = ui;
        this.tasks = tasks;
        this.keyword = keyword;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.FIND
     */
    @Override
    public CommandType getType() {
        return CommandType.FIND;
    }

    /**
     * Executes the command to find tasks.
     */
    @Override
    public void execute() {
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showMatchingList(foundTasks);
    }
}
