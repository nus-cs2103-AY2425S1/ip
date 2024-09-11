package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;

public class TodoDuplicateCommand extends Command {
    /**
     * Executes the "to-do" duplicate handler command which prevents the user from adding
     * a duplicate To-do task to the task list.
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
     * Returns {@code true} as the chatbot should continue running after executing the "to-do" duplicate handler command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
