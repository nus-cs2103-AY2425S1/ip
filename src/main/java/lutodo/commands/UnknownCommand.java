package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

/**
 * Represents an unknown command.
 */
public class UnknownCommand extends Command{

    private final String commandMessage;

    /**
     * Constructs an UnknownCommand object with the command message.
     *
     * @param commandMessage The command message that cannot be understood.
     */
    public UnknownCommand(String commandMessage) {
        this.commandMessage = commandMessage;
    }


    /**
     * Shows to the user that the command cannot be understood.
     *
     * @param tasks   The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Sorry to say that I don't know what does \"" + commandMessage + "\" means. " +
                "Anyway, have a good day :)");
    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "UnknownCommand: " + commandMessage;
    }
}
