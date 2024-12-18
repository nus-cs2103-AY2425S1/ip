package barry.command;

import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;
import barry.task.Task;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given input given by the user.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructs a FindCommand with the specified input.
     *
     * @param input The input given by user to search for in the tasks.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command.
     * Displays the matching tasks to the user
     *
     * @param allTasks The list of all tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for all tasks.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ArrayList<Task> output = new ArrayList<>();

        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.getTask(i);
            if (task.toString().contains(input)) {
                output.add(task);
            }
        }
        ui.showFindResults(output);
    }
}
