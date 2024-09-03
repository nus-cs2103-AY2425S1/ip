package command;
import task.TaskList;
import utilities.Parser;

/**
 * UnmarkCommand class is used to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;
    private TaskList taskList;

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
        System.out.println(Parser.addHorizontalLinesAndIndentation("Nice! I've marked this task as undone:\n" + taskList.get(index - 1)));
    }
}
