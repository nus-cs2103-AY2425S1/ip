package command;

import task.Task;
import task.TaskList;
import utilities.Parser;

public class DeleteCommand extends Command {
    private int index;
    private TaskList taskList;

    public DeleteCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

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
