package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;

/**
 * Class that represents the "bye" command.
 */
public class ByeCommand extends Command {
    /**
     * Does not do anything as the purpose of the "bye" command is to set the processing state to false
     * to shut down the chatbot.
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
     * Returns {@code false} as the chatbot should shut down after executing the "bye" command.
     * @return {@code false}
     */
    @Override
    public boolean continueProcessing() {
        return false;
    }
}
