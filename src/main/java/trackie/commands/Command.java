package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;
/**
 * Represents an abstract command in the task management application.
 * This class serves as a base for all concrete command implementations.
 */
public abstract class Command {
    protected String[] arguments;
    private boolean isExit;

    /**
     * Constructs a new Command with the specified exit status.
     *
     * @param isExit true if this command should cause the program to exit, false otherwise
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks if this command should cause the program to exit.
     *
     * @return true if this command should cause the program to exit, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command. This method must be implemented by concrete subclasses.
     *
     * @param tasklist the TaskList to operate on
     * @param storage the storage system to interact with
     */
    public abstract String execute(TaskList tasklist, Storage storage) throws TrackieException;
}
