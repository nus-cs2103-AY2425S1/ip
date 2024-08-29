package command;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;
import ui.CommandLineUi;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /** An optional task field that is not used in this command. */
    protected Task task;

    /**
     * Executes the command by listing all tasks in the task list and
     * displaying them in the command line interface.
     *
     * @param tasklist The TaskList containing the tasks to be listed.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        List<Task> tasks = tasklist.getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            ui.speakLine((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
