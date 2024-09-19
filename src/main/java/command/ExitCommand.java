package command;

import task.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The ExitCommand class terminates the program
 */
public class ExitCommand extends UserCommand {
    @Override 
    public boolean continueRunning() {
        return false;
    }

    /**
     * Exits the program
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        this.setResponse("Bye. Hope to see you again soon!");
        ui.exit(this.getResponse());
    }
}