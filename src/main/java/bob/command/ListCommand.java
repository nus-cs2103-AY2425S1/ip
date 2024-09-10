package bob.command;

import bob.UI;
import bob.tasks.TaskList;

/**
 * ListCommand is a child class of Command
 * It prints the event TaskList when executed
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class ListCommand extends Command {

    /**
     *  Constructor for ListCommand
     *  Sets parent parameter isRunning to true
     */
    public ListCommand() {
        super(true);
    }

    /**
     * Prints the TaskList given
     *
     * @param taskList TaskList to be printed
     */
    public void execute(TaskList taskList) {
        UI.printTaskList(taskList);
    }
}
