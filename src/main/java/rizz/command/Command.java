package rizz.command;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;

<<<<<<< HEAD
/**
 * Represents an abstract command that can be executed in the Rizz application.
 * Subclasses of Command must implement the execute() method to define specific behaviors.
 */
=======
>>>>>>> branch-A-CodingStandard
public abstract class Command {

    /**
     * Executes the command. This method must be implemented by any subclass of Command.
     * The execution of the command typically involves modifying the TaskList, interacting with the UI,
     * and possibly saving data to storage.
     *
     * @param tasks   The TaskList that the command operates on.
     * @param ui      The Ui instance that handles interaction with the user.
     * @param storage The Storage instance used for saving/loading tasks.
     * @throws IOException If an I/O error occurs during execution (e.g., when saving tasks).
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
<<<<<<< HEAD
=======
    
>>>>>>> Level-9
}

