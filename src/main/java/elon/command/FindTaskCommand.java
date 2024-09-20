package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 */
public class FindTaskCommand extends Command {
    private String searchWord;

    /**
     * Creates a FindTaskCommand with the specified search keyword.
     *
     * @param searchWord the keyword to search for in the task list
     */
    public FindTaskCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Executes the find task command by searching for tasks in the task list that match the given keyword
     * and returning a message from Ui with the matching tasks.
     *
     * @param list the task list to search through
     * @param ui the user interface to interact with the user
     * @param storage the storage
     * @return a string representing the matching tasks
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = list.findTasks(searchWord);
        return ui.showMatchingTasks(matchingTasks);
    }
}
