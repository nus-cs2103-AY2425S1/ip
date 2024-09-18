package henry.command;

import java.util.ArrayList;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with finding task with the particular keyword.
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Returns tasks with the keyword given by user.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param ui Instance of an Ui class that interacts with the user.
     * @return List of tasks with the given keyword.
     * @throws HenryException If no matching task is found.
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        String[] words = this.input.split(" ");

        if (words.length == 1) {
            throw new HenryException("The find description is wrong!! "
                    + "Ensure that you include the keyword you want to find "
                    + "Example: find book");
        }

        ArrayList<Task> tasks = taskList.getTasks();

        int numOfMatches = 0;
        StringBuilder string = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String keyword = this.input.replaceFirst("find ", "");
            if (task.getDescription().contains(keyword)) {
                string.append(i + 1).append(".").append(task).append("\n");
                numOfMatches++;
            }
        }

        if (numOfMatches == 0) {
            throw new HenryException("There is no matching task.");
        }

        return string.toString();
    }
}
