package kotori.command;

import kotori.taskList.TaskList;
import static kotori.ui.Ui.printMessage;
import static kotori.ui.Ui.printListWithMessages;

public class FindCommand extends Command{
    private TaskList taskList;
    private String target;

    public FindCommand (TaskList taskList, String target) {
        this.taskList = taskList;
        this.target = target;
    }

    @Override
    public void execute() {
        TaskList result = taskList.findAll(target);
        if (taskList.isEmpty()) {
            printMessage("Sorry~ There is no matching task");
        } else {
            printListWithMessages(result, "These are(is) the task(s) that match the description");
        }
    }
}
