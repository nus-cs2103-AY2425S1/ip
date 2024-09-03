package command;
import task.TaskList;
import utilities.Parser;
import utilities.Storage;

/**
 * MarkCommand class is used to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    private TaskList taskList;

    /**
     * Constructor for MarkCommand.
     * @param index The index of the task to be marked as done, starting from 1.
     * @param taskList The task list that the task is to be marked as done from.
     */
    public MarkCommand(int index, TaskList taskList) {
        this.index = index - 1;
        this.taskList = taskList;
    }

    /**
     * Executes the mark command.
     */
    @Override
    public void execute() {
        taskList.markTaskAsDone(index - 1);
        new Storage("data/duke.txt").save(taskList);
        System.out.println(Parser.addHorizontalLinesAndIndentation("Nice! I've marked this task as done:\n" + taskList.get(index - 1)));
    }
}