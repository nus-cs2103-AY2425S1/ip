package command;
import task.Task;
import task.TaskList;

/**
 * ListCommand class is used to list and print all tasks in given task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private String lString;

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
        lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task;
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
    }

    @Override
    public String toString() {
        return lString;
    }
}
