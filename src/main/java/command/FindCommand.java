package command;

import java.util.List;

import exceptions.TaskNotFoundException;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command to find a task based on keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructs an FindCommand with the given input string.
     *
     * @param s The input string that contains the keyword that we search for.
     */
    public FindCommand(String s) {
        super(s);
    }

    /**
     * creates a list of tasks that contain the given keyword
     *
     * @param t  The task list to operate on.
     * @param s  The storage to use for saving/loading data.
     * @param ui The user interface to interact with the user.
     * @return the string representation of the tasks
     */
    public String execute(TaskList t, Storage s, Ui ui, Parser p) throws TaskNotFoundException {
        String res = "";
        List<Task> found = t.findTask(getInput().substring(5));
        if (found.isEmpty()) {
            throw new TaskNotFoundException();
        }
        res += ui.findingTask();
        for (Task tsk : found) {
            res += tsk.toString();
            res += "\n";
        }
        return res;
    }
}
