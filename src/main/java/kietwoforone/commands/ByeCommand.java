package kietwoforone.commands;

import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user exits the chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to exit the user out of the chatbot.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns a boolean to determine whether to close the chatbot.
     * True if the command closes the chatbot and false otherwise.
     *
     * @return Boolean to determine whether to close the chatbot.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the string representation of the ByeCommand object.
     *
     * @return String representation of the ByeCommand object.
     */
    @Override
    public String toString() {
        return "Bye";
    }
}
