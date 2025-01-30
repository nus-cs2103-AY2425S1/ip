package star.command;

import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents the Command to find tasks in the task list that contain a specific keyword.
 * Inherits from the Command class.
 */
public class FindCommand extends Command {
    private static final String FIND_MSG = "these tasks match what you were looking for!";
    private static final String UNFIND_MSG = "none of the tasks in the task list match what "
            + "you were looking for :(";
    private String keyword;

    /**
     * Constructs a findCommand that finds tasks containing the given String keyword.
     * @param keyword which is the given String that is used to find tasks containing that keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ((tasks.findTasks(this.keyword)).isEmpty())
                ? ui.printList(tasks.findTasks(this.keyword), UNFIND_MSG)
                : ui.printList(tasks.findTasks(this.keyword), FIND_MSG);
    }
}
