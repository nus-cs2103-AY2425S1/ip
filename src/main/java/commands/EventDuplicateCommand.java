package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;

/**
 * Class that represents the non-user command passed when a duplicate {@code Event} task is detected.
 */
public class EventDuplicateCommand extends Command {
    /**
     * Executes the "event" duplicate handler command which prevents the user from adding
     * a duplicate Event task to the task list.
     *
     * @param tasks            The task list to use.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        return ui.getDuplicateDeny();
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "event" duplicate
     * handler command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
