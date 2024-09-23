package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object with the specified description.
     *
     * @param description The command description.
     */
    public ListCommand(String description) {
        super(description);
    }

    /**
     * Executes the command to display the list of tasks.
     *
     * @param tasks   The list of tasks (not used in this method).
     * @param ui      The UI component that handles displaying the task list.
     * @param storage The storage component (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayList();
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the list command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
