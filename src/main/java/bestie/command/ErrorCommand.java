package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates an error command that allows the Bestie chatbot to display error message to user.
 */
public class ErrorCommand extends Command{

    private String errorMessage;

    /**
     * Displays error message to the user.
     *
     * @param errorMessage Error message to be displayed.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Executes the error command by printing the error message on the UI.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     * @return String displaying error message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showErrorMessage(this.errorMessage);
    }

}
