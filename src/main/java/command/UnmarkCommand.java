package command;
import task.TaskList;
import utilities.Storage;

/**
 * UnmarkCommand class is used to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;
    private TaskList taskList;
    private String message;

    /**
     * Constructor for UnmarkCommand.
     * @param index The index of the task to be marked as undone, starting from 1.
     * @param taskList The task list that the task is to be marked as undone from.
     */
    public UnmarkCommand(int index, TaskList taskList) {
        this.index = index - 1;
        this.taskList = taskList;
    }

    /**
     * Executes the unmark command.
     */
    @Override
    public void execute() {
        taskList.markTaskAsUndone(index);
        new Storage("./data/duke.txt").save(taskList);
        message = "Nice! I've marked this task as undone:\n" + taskList.get(index);
    }

    @Override
    public String toString() {
        return message;
    }
}
