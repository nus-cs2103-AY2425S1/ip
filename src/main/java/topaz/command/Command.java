package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

/**
 * Represents a command that can be executed in the task management system.
 * Each command has a keyword associated with it and may change the taskList state when executed.
 */

public class Command {
    protected boolean isExit;
    protected String keyword;

    /**
     * Constructs a Command with the specified keyword.
     *
     * @param keyword The keyword represents type of the command.
     */
    public Command(String keyword) {
        this.isExit = false;
        this.keyword = keyword;
    }

    /**
     * Executes the command. The actual behavior of this method is defined by subclasses.
     *
     * @param tasks    The TaskList that contains the list of tasks in current system.
     * @param ui       The Ui object that handles user interaction.
     * @param storage  The Storage object that handles saving and loading data.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return null;
    }

    public boolean isExit() {
        return isExit;
    }
}
