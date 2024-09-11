package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;

public class DeadlineDuplicateCommand extends Command {
    /**
     * Executes the "deadline" duplicate handler command which prevents the user from adding
     * a duplicate Deadline task to the task list.
     *
     * @param tasks            The task list to use.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        return ui.getDuplicateDeny();
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "deadline" duplicate handler command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
