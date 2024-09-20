package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import utilities.Printer;

/**
 * Represents a command that finds tasks occurring on a specified date.
 */
public class FindDateCommand extends Command {
    public static String[] params = new String[] { "findDate" };
    public static int paramCount = 1;
    private final String date;

    /**
     * Constructs a {@code FindDateCommand} with the specified date.
     *
     * @param date the date to search for tasks.
     */
    public FindDateCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the command to find tasks that occur on the specified date.
     *
     * @param tasks the list of tasks.
     * @param ui the UI for user interaction.
     * @param storage the storage handler.
     * @param execStack the execution stack for command history.
     * @return a formatted string listing the tasks that occur on the specified date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        String[] tasksFound = tasks.findTasksOn(this.date);
        String[] toPrint = new String[tasksFound.length + 2];

        toPrint[0] = String.format("Here are the tasks occuring on %s:", this.date);
        toPrint[tasksFound.length + 1] = String.format("Number of tasks found: %d", tasksFound.length);
        System.arraycopy(tasksFound, 0, toPrint, 1, tasksFound.length);

        return Printer.format(toPrint);
    }

    /**
     * Undoes the command, but does not perform any actions since the command does not change the state.
     *
     * @param tasks the list of tasks.
     * @param ui the UI for user interaction.
     * @param storage the storage handler.
     * @return an empty string, as no undo action is required.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        // Do nothing
        return "";
    }


    /**
     * Checks if this {@code FindDateCommand} is equal to another object.
     * Two {@code FindDateCommand} instances are considered equal if they have the same date.
     *
     * @param obj the object to compare this command with.
     * @return {@code true} if the object is a {@code FindDateCommand} with the same date; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindDateCommand temp) {
            return this.date.equals(temp.date);
        }
        return false;
    }
}
