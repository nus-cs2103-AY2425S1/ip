package kotori.command;

import kotori.taskList.TaskList;
import static kotori.ui.Ui.printListWithMessages;
import static kotori.ui.Ui.printMessages;

public class FindCommand extends Command{
    private TaskList taskList;
    private String target;

    public FindCommand (TaskList taskList, String target) {
        this.taskList = taskList;
        this.target = target;
    }

    @Override
    public String execute() {
        TaskList result = taskList.findAll(target);
        if (taskList.isEmpty()) {
            return printMessages("Sorry~ There is no matching task");
        } else {
            return printListWithMessages(result, "These are(is) the task(s) that match the description");
        }
    }
}
