package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;

import java.util.ArrayList;

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
     * @throws ReminderebotException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasklist.length(); i++) {
            Task task = tasklist.getTask(i);
            if (task.contains(keyword)) {
                tasksFound.add(task);
            }
        }
        ui.findTask(tasksFound);

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
