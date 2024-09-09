package revir.user.command;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a command to find tasks containing a keyword.
 */
public class Find extends Command {
    private String keyword;

    /**
     * Constructs a new Find command with the specified keyword.
     *
     * @param keyword the keyword to search for
     */
    public Find(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which searches for tasks containing the keyword.
     *
     * @param ui The user interface object used to display the result of the command.
     * @param taskList The task list object to search for the keyword.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        String list = taskList.find(this.keyword);
        ui.showResult("Find:\n" + list);
    }

}
