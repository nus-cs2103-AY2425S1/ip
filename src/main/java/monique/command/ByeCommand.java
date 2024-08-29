package monique.command;

import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command to exit the application.
 * This command saves the current task list to storage and displays a goodbye message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a <code>ByeCommand</code> object.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the exit command by saving the task list to storage and displaying a goodbye message.
     *
     * @param tasks the <code>TaskList</code> containing the tasks to be saved
     * @param ui the <code>Ui</code> instance used to display the goodbye message
     * @param storage the <code>Storage</code> instance used to manage data persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTaskList());
        ui.showGoodbye();
    }

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return false, indicating that the chatbot will terminate after the bye command
     */
    public boolean isActive() {
        return false;
    }
}
