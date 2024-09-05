package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

/**
 * Represents a command to mark a certain task a completed.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a new MarkCommand with the given arguments.
     *
     * @param arguments An array of Strings containing the arguments from the user\.
     *                  Typically, this would include the index of the task to be marked completed.
     */
    public MarkCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    /**
     * Executes the mark command.
     * This method causes the tasklist to mark a task as completed.
     * The user is then notified of the tasks' completion by the chatbot.
     *
     * @param tasklist The TaskList object from which a task will be marked.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.markTask(arguments);
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
