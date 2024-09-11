package rasputin.command;

import rasputin.task.RasputinException;
import rasputin.task.TaskList;

import rasputin.gui.Ui;

/**
 * Represents command to print out the current list of tasks.
 */
public class PrintListCommand extends Command {

    TaskList tasks;
    public PrintListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the current list of tasks.
     */
    @Override
    public String execute() {
        return Ui.getList(tasks);
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
