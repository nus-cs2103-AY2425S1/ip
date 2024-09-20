package command;
import task.TaskList;
public class FindCommand extends Command {
    private String keyword;
    private TaskList taskList;
    private String message;

    public FindCommand(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        TaskList filteredTaskList = taskList.findTasks(keyword);
        message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filteredTaskList.size(); i++) {
            message += (i + 1) + ". " + filteredTaskList.get(i) + "\n";
        }
    }

    @Override
    public String toString() {
        return message;
    }
}
