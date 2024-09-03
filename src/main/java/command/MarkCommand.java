package command;
import task.TaskList;
import utilities.Parser;
import utilities.Storage;

public class MarkCommand extends Command {
    private int index;
    private TaskList taskList;

    public MarkCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.markTaskAsDone(index - 1);
        new Storage("data/duke.txt").save(taskList);
        System.out.println(Parser.addHorizontalLinesAndIndentation("Nice! I've marked this task as done:\n" + taskList.get(index - 1)));
    }
}