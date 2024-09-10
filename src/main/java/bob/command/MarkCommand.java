package bob.command;

import bob.UI;
import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;

/**
 * MarkCommand is a child class of Command
 * It takes in an index during initialization and then marks the task at that index from the TaskList as completed
 * when executed.
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructor for MarkCommand
     * Sets parent parameter isRunning to true
     *
     * @param index Will be used when the execute method is called to mark the task at that index
     */
    public MarkCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Marks the task from the TaskList at the specified index
     * It then prints a message to indicate that the task has been marked
     *
     * @param taskList TaskList in which the task to be marked is
     * @throws InvalidTaskNumberException Is thrown when user inputs an invalid Task Number
     */
    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        taskList.markTaskAtIndex(index);
        UI.printMarkTask();
    }
}
