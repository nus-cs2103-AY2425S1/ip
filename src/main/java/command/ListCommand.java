package command;

import task.TaskList;

/**
 * Command which prints the task list when executed.
 *
 * @author IsaacPangTH
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList list) {
        if (list.isEmpty()) {
            him.Ui.sayEmptyList();
        } else {
            him.Ui.sayList(list);
        }
    }
}
