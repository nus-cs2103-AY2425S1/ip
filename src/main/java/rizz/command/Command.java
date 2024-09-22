package rizz.command;
import rizz.source.TaskList;


/**
 * Represents an abstract command that can be executed in the Rizz application.
 * Subclasses of Command must implement the execute() method to define specific behaviors.
 */

public abstract class Command {

    /**
     * Executes the command. This method must be implemented by any subclass of Command.
     * The execution of the command typically involves modifying the TaskList, interacting with the UI,
     * and possibly saving data to storage.
     *
     * @param tasks   The TaskList that the command operates on.
     */
    public abstract String execute(TaskList tasks);

}

