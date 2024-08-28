package henry.command;

import java.util.ArrayList;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with finding task with the particular keyword
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks with the keyword given by user
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     */
    public void execute(TaskList taskList, Ui ui) throws HenryException {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("\nHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(this.input)) {
                System.out.println(i + 1 + "." + task.toString());
            }
        }
        System.out.println();
    }
}
