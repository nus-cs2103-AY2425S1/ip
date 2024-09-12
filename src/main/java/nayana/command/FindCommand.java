package nayana.command;

import java.util.ArrayList;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Represents a command to find and display tasks matching a specific search query.
 */
public class FindCommand extends Command {
    private String findValue;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param findValue The search query to find matching tasks.
     */
    public FindCommand(String findValue) {
        super();
        this.findValue = findValue;
    }

    /**
     * Executes the command to find and display tasks that match the search query.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage system for tasks.
     * @throws NayanaException If an error occurs while processing the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        ArrayList<Task> foundTasks = tasks.findTasks(this.findValue);
        assert ui != null;
        ui.printFoundTasks(foundTasks);
    }

    /**
     * Indicates whether the command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the search query used for finding tasks.
     *
     * @return The search query.
     */
    public String getFindValue() {
        return this.findValue;
    }
}
