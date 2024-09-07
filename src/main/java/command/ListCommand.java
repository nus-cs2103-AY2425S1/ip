package command;

import task.TaskList;

/**
 * Command which returns the task list when executed.
 *
 * @author IsaacPangTH
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList list) {
        if (list.isEmpty()) {
            return him.Ui.sayEmptyList();
        } else {
            return him.Ui.sayList(list);
        }
    }
}
