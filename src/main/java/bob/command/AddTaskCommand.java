package bob.command;

import bob.UI;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * AddTaskCommand is a child class of Command
 * It takes in a task during initialization and then adds the task to the TaskList when executed.
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class AddTaskCommand extends Command {

    private final Task task;

    /**
     * Constructor for AddTaskCommand
     * Sets parent parameter isRunning to true
     *
     * @param task Task to be added to a TaskList when executed
     */
    public AddTaskCommand(Task task) {
        super(true);
        this.task = task;
    }

    /**
     * Adds the task that was initially in the initialization to the given TaskList object
     *
     * @param taskList TaskList which the task should be added into
     */
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        UI.printAddTask(this.task);
        UI.printCurrentTaskListStatus(taskList);
    }
}
