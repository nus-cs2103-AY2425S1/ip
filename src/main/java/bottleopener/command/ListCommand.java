package bottleopener.command;

import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * The {@code ListCommand} class is responsible for displaying the current list of tasks.
 * It retrieves the task list from the {@code Tasklist} object and returns it in a formatted string.
 */
public class ListCommand implements Command {
    private Tasklist tasklist;

    /**
     * Constructs a {@code ListCommand} object with the given task list.
     *
     * @param tasklist The list of tasks to be displayed.
     */
    public ListCommand(Tasklist tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Executes the list command by retrieving and formatting the current task list.
     * The task list is wrapped with spacers for better readability in the UI.
     *
     * @return A string representing the formatted task list, wrapped with spacers.
     */
    @Override
    public String runCommand() {
        return Ui.wrapSpacer(tasklist.showTasklist());
    }

}
