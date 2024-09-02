package command;
import task.Task;
import task.TaskList;
import utilities.Parser;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        String lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task;
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
        lString = Parser.addHorizontalLinesAndIndentation(lString);
        System.out.println(lString);
    }
}
