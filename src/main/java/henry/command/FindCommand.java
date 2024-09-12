package henry.command;

import java.util.ArrayList;

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
     */
    public String execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder string = new StringBuilder("\nHere are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(this.input)) {
                string.append(i + 1).append(".").append(task).append("\n");
            }
        }
        return string.toString();
    }
}
