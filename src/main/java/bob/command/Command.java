package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command executes tasks.
 */
public class Command {

    private String input;
    private boolean isExit;

    /**
     * Constructor to initialise Command.
     *
     * @param input
     */
    public Command(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Constructor to initialise Command.
     */
    public Command() {
        this.input = "";
        this.isExit = false;
    }

    /**
     * Returns a String representation of the text that users will see upon entering some input.
     *
     * @param taskList Task List
     * @param storage storage to store updates to task list after each successful command.
     * @param ui ui to display relevant info to user for each input.
     * @return
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "Invalid Command given. Please enter a valid command.";
    }

    public String getInput() {
        return input;
    }

    /**
     * Returns true if the command results in the program terminating.
     *
     * @return
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets isExit to true.
     */
    public void updateIsExitToTrue() {
        this.isExit = true;
    }
}
