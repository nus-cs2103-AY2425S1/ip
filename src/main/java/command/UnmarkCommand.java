package command;
import task.TaskList;
import utilities.Parser;

public class UnmarkCommand extends Command {
    private int index;
    private TaskList taskList;

    public UnmarkCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.markTaskAsUndone(index);
        System.out.println(Parser.addHorizontalLinesAndIndentation("Nice! I've marked this task as undone:\n" + taskList.get(index - 1)));
    }
}
