package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.Storage;

/**
 * Represents a command to exit the application.
 */
public class CommandBye extends Command {

    /**
     * Constructs a CommandBye object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandBye(String[] inputs) {
    }

    /**
     * Executes the command to exit the application.
     */
    @Override
    public void execute() {
        Storage.saveTasksToFile();
        Ui.printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}