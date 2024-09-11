package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;

/**
 * Class that represents the "bye" command.
 */
public class ByeCommand extends Command {
    /**
     * Executes the "bye" command by returning the farewell message.
     *
     * @param tasks            The task list to use if necessary.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        return ui.farewell();
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
