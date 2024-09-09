package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Command {

    public ListCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "What the sigma? You have no tasks!";
        } else {
            StringBuilder s = Ui.buildList(tasks);
            return "You want a list? You got it!\n" + s.toString();
        }
    }

}
