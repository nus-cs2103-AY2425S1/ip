package bob.command;

import bob.UI;
import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * DeleteCommand is a child class of Command
 * It takes in an index during initialization and then removes the task at that index from the TaskList when executed.
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for ByeCommand
     * Sets parent parameter isRunning to true
     *
     * @param index Will be used when the execute method is called to remove the task at that index
     */
    public DeleteCommand(int index) {
        super(true);
        this.index = index;
    }

    /**
     * Deletes the task from the TaskList at the specified index
     * It then prints a message to indicate a successful deletion
     *
     * @param taskList TaskList from which the task will be deleted from
     * @throws InvalidTaskNumberException Is thrown when user inputs an invalid Task Number
     */
    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        Task delTask = taskList.removeTaskAtIndex(this.index);
        UI.printDeleteTask(delTask);
        UI.printCurrentTaskListStatus(taskList);
    }
}
