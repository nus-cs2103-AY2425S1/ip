package Command;

import Task.RasputinException;
import Task.TaskList;
import Task.Task;

import Ui.Ui;

/**
 * Represents a command to find a task with description containing the keyword to search with.
 */
public class FindCommand extends Command {

    private String input;
    private TaskList tasks;
    private TaskList foundTasks;

    public FindCommand(String input, TaskList tasks) {
        this.input = input;
        this.tasks = tasks;
        this.foundTasks = new TaskList();
    }

    /**
     * Searches the TaskList for tasks with descriptions containing the given search keyword.
     * If no matching tasks are found, print a message saying no found matches.
     * Else, print TaskList of matching tasks.
     *
     * @throws RasputinException If command is missing the keyword to search with.
     */
    @Override
    public void execute() throws RasputinException {
        String[] cmdSplit = input.split(" ", 2);
        if (cmdSplit.length < 2) {
            throw new RasputinException("ERROR! Find command requires a keyword to search with.");
        }
        String keyword = cmdSplit[1];
        for (Task task : tasks.getTasks()) {
            String desc = task.getDescription();
            if (desc.contains(keyword)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            Ui.printText("No matching tasks in list.");
        } else {
            String output = String.format("Here are the matching tasks in your list.\n%s", foundTasks.toString());
            Ui.printText(output);
        }
    }

    /**
     * Always returns false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
