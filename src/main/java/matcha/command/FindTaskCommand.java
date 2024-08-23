package matcha.command;

import matcha.exception.MatchaException;
import matcha.storage.Storage;
import matcha.task.Task;
import matcha.tasklist.TaskList;
import matcha.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a given keyword.
 */
public class FindTaskCommand extends Command {
    private String[] inputWords;

    /**
     * Constructor for a new FindTaskCommand.
     *
     * @param inputWords The input words from the user.
     */
    public FindTaskCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    /**
     * Executes the command to find tasks that contain a given keyword.
     * Prints out the tasks that contain the keyword.
     *
     * @param tasks The list of tasks to search from.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save the tasks.
     * @throws MatchaException If the keyword is not provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException {
        if (inputWords.length != 2) {
            throw new MatchaException("Please enter a keyword to search for.");
        }

        String keyword = inputWords[1];
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(keyword);

        if (matchingTasks.size() <= 0) {
            System.out.println("There are no tasks that contain the keyword: " + keyword);
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            String task = (i + 1) + ". " + matchingTasks.get(i);
            System.out.println("\t" + task);
        }
    }
}
