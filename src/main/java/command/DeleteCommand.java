package command;

import task.Task;
import task.TaskList;
import utilities.Parser;

/**
 * DeleteCommand class is used to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList taskList;

    /**
     * Constructor for DeleteCommand.
     * @param index The index of the taskList to be deleted, starting from 1.
     * @param taskList The task list that the task is to be deleted from.
     */
    public DeleteCommand(int index, TaskList taskList) {
        this.index = index - 1;
        this.taskList = taskList;
    }

    /**
     * Executes the delete command.
     */
    @Override
    public void execute() {
        Task task = taskList.remove(index - 1);
        System.out.println(Parser
                .addHorizontalLinesAndIndentation(
                        String.format("Noted. I've removed this task:\n"
                                + task + " Now you have %d tasks in the list.",
                                taskList.size())));
    }

}
