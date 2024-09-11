package command;
import task.TaskList;

public class SortCommand extends Command {
    private TaskList taskList;

    public SortCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.sort();
    }

    @Override
    public String toString() {
        return "Sorted tasks";
    }
}
