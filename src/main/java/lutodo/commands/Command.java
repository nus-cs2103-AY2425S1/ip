package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

/**
 * Represents a command. As abstract class, all types of Command inherit from this class.
 */
public abstract class Command {

    /**
     * Executes the command and save changes to the task list file if needed.
     *
     * @param tasks The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    public abstract void execute(TaskList tasks, Storage storage);

    /**
     * Returns whether this is an exit command.
     *
     * @return whether this is an exit command.
     */
    public abstract boolean isExit();

    /**
     * Judges if two commands can be considered same, mainly used in tests.
     *
     * @param otherCommand The other command to be compared.
     * @return Whether this command and the other command can be seen as same commands.
     */
    @Override
    public boolean equals(Object otherCommand){
        return toString().equals(otherCommand.toString());
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "Command";
    }
}
