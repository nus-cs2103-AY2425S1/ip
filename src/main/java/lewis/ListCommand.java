package lewis;

import java.util.ArrayList;

/**
 * This class implements a list command which tells Lewis to display the current
 * tasklist
 */
public class ListCommand extends Command {
    /** The singular instance of ListCommand. */
    private static final ListCommand LIST_COMMAND = new ListCommand();
    /** Private constructor for ListCommand */
    private ListCommand() {
    }

    /**
     * Factory method to instantiate a list command
     * @return The singular instance of ListCommand
     */
    public static ListCommand of() {
        return LIST_COMMAND;
    }

    /**
     * Returns a description for the user on the usage of this command
     * @return a string description
     */
    public static String getHelpDescription() {
        return "Displays all tasks in the current tasklist.\n"
                + "Usage: list";
    }
    /**
     * Executes the command. Subclasses of this class must implement this logic.
     */
    @Override
    public void execute() {
        TaskList.showAllTasks();
        ArrayList<String> tasksToPrint = TaskList.allTasksToString();
        Ui.printList(tasksToPrint);
    }
}
