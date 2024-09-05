package bob.command;

import bob.UI;
import bob.tasks.TaskList;

/**
 * ByeCommand is a child class of Command
 * Sets the isRunning param of the parent class to be false signalling the end of the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand
     * Sets parent parameter isRunning to true
     */
    public ByeCommand() {
        super(false);
    }

    /**
     * Prints the bye message to signal the end of the program
     *
     * @param taskList TaskList is not used in this method
     */
    public void execute(TaskList taskList) {
        UI.printExit();
    }
}
