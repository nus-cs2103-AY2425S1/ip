package sigma.command;
import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

import java.util.List;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Commands {

    public ListCommand(String[] split) {
        super(split);
    }

    /**
     * Lists all tasks in the task list.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            s.append(i + ". " + tasks.get(i - 1).toString() + "\n");
        }
        if (tasks.isEmpty()) {
            ui.print("What the sigma? You have no tasks!");
        } else {
            ui.print("You want a list? You got it!\n" + s.toString());
        }
    }

}
