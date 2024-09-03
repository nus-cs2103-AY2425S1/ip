package command;
import task.TaskList;
import utilities.Parser;
public class FindCommand extends Command {
    private String keyword;
    private TaskList taskList;

    public FindCommand(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        TaskList filteredTaskList = taskList.findTasks(keyword);
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filteredTaskList.size(); i++) {
            message += (i + 1) + ". " + filteredTaskList.get(i) + "\n";
        }
        System.out.println(Parser.addHorizontalLinesAndIndentation(message));
    }
    
}
