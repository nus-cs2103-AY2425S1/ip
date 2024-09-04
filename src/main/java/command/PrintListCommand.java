package command;

import task.TaskList;

import ui.Ui;

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
    public void execute() {
        Ui.printList(tasks);
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
