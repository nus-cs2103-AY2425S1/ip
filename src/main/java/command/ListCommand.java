package command;
import task.Task;
import task.TaskList;
import utilities.Parser;

/**
 * ListCommand class is used to list and print all tasks in given task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor for ListCommand.
     * @param taskList The task list to be listed.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the list command.
     */
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
