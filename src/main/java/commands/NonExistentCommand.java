package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;

/**
 * Class that represents a non-existent command.
 */
public class NonExistentCommand extends Command {
    /**
     * Does not do anything since it is a non-existent command.
     *
     * @param tasks   The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui The Ui object used to generate the string to print.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return "";
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing a non-existent command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
