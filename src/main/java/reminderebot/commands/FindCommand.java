package reminderebot.commands;

import java.util.ArrayList;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.task.Task;

/**
 * The FindCommand class represents a command to find a task with
 * a matching keyword from Tasklist.
 */
public class FindCommand extends Command {
    private final String keyword;
    /**
     * Instantiate a FindCommand instance.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Finds tasks that contain the word after find.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing Find command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasklist.length(); i++) {
            Task task = tasklist.getTask(i);
            if (task.contains(keyword)) {
                tasksFound.add(task);
            }
        }
        return ui.findTask(tasksFound);

        // if not found:
        // handle error
    }

    /**
     * Find does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
