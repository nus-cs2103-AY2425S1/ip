package bob.command;

import bob.UI;
import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;

/**
 * UnmarkCommand is a child class of Command
 * It takes in an index during initialization and then unmarks the task at that index from the TaskList as uncompleted
 * when executed.
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructor for UnmarkCommand
     * Sets parent parameter isRunning to true
     *
     * @param index Will be used when the execute method is called to unmark the task at that index
     */
    public UnmarkCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Unmarks the task from the TaskList at the specified index
     * It then prints a message to indicate that the task has been unmarked
     *
     * @param taskList TaskList in which the task to be unmarked is
     * @throws InvalidTaskNumberException Is thrown when user inputs an invalid Task Number
     */
    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        taskList.unmarkTaskAtIndex(index);
        UI.printUnmarkTask();
    }
}
